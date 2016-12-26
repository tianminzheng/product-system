package com.tianyalan.product.cache.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalCacheComponent<T> {

	private Date lastUpdateTime;  //该类型数据上一次更新的时间
	
	private Map<String,T> objectMap;
	
	private List<T>  objectList;

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}


	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public Map<String,T> getObjectMap() {
		return objectMap;
	}
	
	/**
	 * 通过一组主键值获取数据列表
	 * @param keys
	 * @return
	 */
	public List<T> getDataListByKeys(Object[] keys){
		List<T> datas = new ArrayList<T>();
		for(Object key:keys){
			if(key==null)
				continue;
			
			datas.add(objectMap.get(key.toString()));
		}
		
		return datas;
	}
	
	/**
	 * 通过一组主键值获取数据MAP
	 * @param keys
	 * @return
	 */
	public Map<String,T> getDataMapByKeys(Object[] keys){
		Map<String,T> dataMap = new HashMap<String,T>();
		for(Object key:keys){
			if(key==null)
				continue;
			
			T data = (T)objectMap.get(key.toString());
			if(data==null)
				continue;
			
			dataMap.put(key.toString(),data);
		}			
		return dataMap;
	}
	
	public T getSingleData(Object key){
		if(key==null)
			return null;
		
		return getObjectMap().get(key.toString());
	}
	
	public void setObjectMap(Map<String,T> objectMap) {
		this.objectMap = objectMap;
	}

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}
}
