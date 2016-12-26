package com.tianyalan.product.cache.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianyalan.product.cache.config.LocalCacheDefParser;
import com.tianyalan.product.cache.config.LocalCacheDefinition;
import com.tianyalan.product.cache.service.DefaultCacheServiceImpl;
import com.tianyalan.product.cache.service.ICacheRefreshObserver;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

public class DataCacheServiceImpl extends DefaultCacheServiceImpl{
	
	private MyBatisDAO myBatisDAO;
	
	private ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1); //定义线程池，并行加载本地缓存数据
	
	private ScheduledExecutorService cacheRefreshService = Executors.newSingleThreadScheduledExecutor(); //定义单线程定时执行器，用于定时刷新缓存
		
	private final Map<Class<?>,LocalCacheComponent<?>> cacheMap = new ConcurrentHashMap<Class<?>,LocalCacheComponent<?>>();
	
	private final Map<Class<?>,CacheLoadWorker> workerMap = new HashMap<Class<?>,CacheLoadWorker>();
	
	private final Map<Class<?>,List<ICacheRefreshObserver>> observersMap = new ConcurrentHashMap<Class<?>,List<ICacheRefreshObserver>>();
	
	private static List<LocalCacheDefinition> cacheDefs;
	
	private static int CACHE_REFRESH_INTERVAL = 2000; //ms

	private static final Logger logger = LoggerFactory.getLogger("CACHE_LOGGER");
	
	/*
	 * 初始化本地缓存，本地缓存保存系统用到的静态数据，或基本不变的数据，
	 * 对于可能变化的数据必须是不需要实时刷新的，可以通过某种策略（待定）定时和数据库进行同步
	 */
	public void init(){
		cacheDefs = LocalCacheDefParser.parse();
		if(cacheDefs==null || cacheDefs.size()==0){
			logger.error("***********************Fail to load the cache definition***********************");
			return;
		}
		
		loadTheCacheData(cacheDefs);
		startRefreshCache();
	}
	
	
	private void loadTheCacheData(List<LocalCacheDefinition> cacheDefs){
		logger.info("DataCacheServiceImpl --->loadTheCacheData()");
		for(LocalCacheDefinition oneCache:cacheDefs){
			if(oneCache==null)
				continue;
			
			Class<?> voCalssType = oneCache.getVOClassObject();
			if(voCalssType==null){
				logger.warn("fail to load the data from table '{}'",oneCache.getTableName());
				continue;
			}
			
			CacheLoadWorker worker = new CacheLoadWorker(oneCache);
			workerMap.put(oneCache.getVOClassObject(), worker);
			
			ex.execute(worker);
		}
	}
	
	private void startRefreshCache(){
		cacheRefreshService.scheduleAtFixedRate(new Runnable() {			
			@Override
			public void run() {
				logger.info("***********start to refresh the cache*********************");
				for(LocalCacheDefinition oneCache:cacheDefs){
					if(!oneCache.isNeedRefresh())
						continue;
					
					refreshCache(oneCache.getVOClassObject());
				}
			}
		}, CACHE_REFRESH_INTERVAL, CACHE_REFRESH_INTERVAL, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public synchronized void addCacheRefreshObserver(List<Class<?>> clazzList,ICacheRefreshObserver observer){
		for(Class<?> clazz:clazzList){
			List<ICacheRefreshObserver> observers = observersMap.get(clazz);
			if(observers==null){
				observers = new ArrayList<ICacheRefreshObserver>();
				observersMap.put(clazz, observers);
			}
			
			observers.add(observer);
		}
	}
	
	/**
	 * 缓存更新，通知观察者
	 * @param clazz
	 */
	private void notifyObserver(Class<?> clazz){
		List<ICacheRefreshObserver> observers = observersMap.get(clazz);
		if(observers==null || observers.size()==0){
			return;
		}
		
		for(ICacheRefreshObserver observer:observers){
			observer.notifyBasicCacheRefresh();
		}
	}
	
	public <T> void refreshCache(Class<T> clzssType){
		if(clzssType==null){
			logger.warn("***************clzssType is null*******************");
			return;
		}
		
		CacheLoadWorker worker = workerMap.get(clzssType);
		if(worker==null){
			logger.warn("can not find the cacheLoadWork for '{}'",clzssType.toString());
			return;
		}
		
		logger.info("DataCacheServiceImpl --->refreshCache() for Type '{}'",clzssType.toString());
		ex.execute(worker);
	}
	
	/**
	 * 获取缓存的该类型所有值的列表
	 */
	@Override
	public <T> List<T> getDataListByType(Class<T> clzss) {
		if(clzss==null)
			return null;
	
		//waiting for initialization		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("unchecked")
		LocalCacheComponent<T> component = (LocalCacheComponent<T>)cacheMap.get(clzss);
		if(component==null)
			return null;
		
		return component.getObjectList();
	}
	/**
	 * 根据主键值，获取改类型的单一缓存数据
	 */
		
	public MyBatisDAO getMyBatisDAO() {
		return myBatisDAO;
	}

	public void setMyBatisDAO(MyBatisDAO myBatisDAO) {
		this.myBatisDAO = myBatisDAO;
	}
	
	private class CacheLoadWorker implements Runnable{
		
		private LocalCacheDefinition cacheDef;
		
		CacheLoadWorker(LocalCacheDefinition cacheDef){
			this.cacheDef = cacheDef;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			Class<?> type = cacheDef.getVOClassObject();
			@SuppressWarnings("rawtypes")
			LocalCacheComponent component = cacheMap.get(type);
			Date current = new Date();
			boolean cacheUpdated = false;
			if(component!=null){
				Map<String,Date> param = new HashMap<String,Date>();
				param.put("lastUpdateTime", component.getLastUpdateTime());
				int updateNumber = (Integer)myBatisDAO.findForObject(cacheDef.getChangeSQLId(),param);
				if(updateNumber==0){
					logger.info("****** No Data changed for table '{}' ******",cacheDef.getTableName());
					return;
				}else{
					cacheUpdated = true;
					logger.info("****** Data changed for table '{}' ******",cacheDef.getTableName());
				}
			}	
			
			loadFromDB(type, current, component,cacheUpdated);
		}
		
		private <T> void loadFromDB(Class<T> type,Date current,LocalCacheComponent<T> component,boolean cacheUpdated){
			logger.info("**********Begin to load the cache data for one table************");
			logger.info("table:{}", cacheDef.getTableName());
			logger.info("mapperSQLId:{}" , cacheDef.getMapperSQLId());
			logger.info("VOClassName:{}" , cacheDef.getVoCalssName());
			logger.info("VOPrimaryKey{}:" ,cacheDef.getVoPrimaryKey());		
			try {
				@SuppressWarnings("unchecked")
				List<T> datas = (List<T>)myBatisDAO.findForList(cacheDef.getMapperSQLId());
				Map<String,T> dataMap = new HashMap<String,T>();
				
				component = new LocalCacheComponent<T>();
				component.setObjectList(datas);
				component.setObjectMap(dataMap);
				component.setLastUpdateTime(current);
				
				cacheMap.put(type, component);
				
				if(cacheUpdated){
					notifyObserver(type);
				}
			} catch (Exception e) {
                logger.error("fail to load the data from " + cacheDef.getTableName(),e);
			}		
		}
		
	}
}