
package com.tianyalan.product.cache.service;

import java.util.List;

public abstract class DefaultCacheServiceImpl implements DataCacheService {

	@Override
	public void addCacheRefreshObserver(List<Class<?>> clazzList,ICacheRefreshObserver observer){
		
	}
	
	@Override
	public <T> void refreshCache(Class<T> clzssType) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> getDataListByType(Class<T> clzss) {
		// TODO Auto-generated method stub
		return null;
	}
}
