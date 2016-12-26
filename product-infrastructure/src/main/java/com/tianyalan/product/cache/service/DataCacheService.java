
package com.tianyalan.product.cache.service;

import java.util.List;

public interface DataCacheService {
	
	/**
	 * 添加静态缓存刷新的观察者
	 * @param observer
	 */
	void addCacheRefreshObserver(List<Class<?>> clazzList,ICacheRefreshObserver observer);
	/**
	 * 刷新本地缓存
	 * @param clzssType
	 */
	<T> void refreshCache(Class<T> clzssType);
	
	/**
	 * 获取缓存的该类型所有值的列表
	 */
	<T> List<T> getDataListByType(Class<T> clzss);

}
