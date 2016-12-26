package com.tianyalan.product.core.dao.cache.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.cache.service.DataCacheService;
import com.tianyalan.product.cache.service.ICacheRefreshObserver;
import com.tianyalan.product.core.dao.cache.CacheService;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;

@Service("cacheService")
public class CacheServiceImpl implements CacheService, ICacheRefreshObserver {

	@Autowired
	private DataCacheService dataCacheService;
	
	@PostConstruct
	public void init(){
		List<Class<?>> clazzList = new ArrayList<Class<?>>();
		clazzList.add(Vendor.class);
		clazzList.add(VendorSystem.class);
		
		dataCacheService.addCacheRefreshObserver(clazzList, this);
	}
	
	@Override
	public List<Vendor> getVendors() {
		return dataCacheService.getDataListByType(Vendor.class);
	}

	@Override
	public List<Vendor> getVendorsWithVendorSystem() {
		List<Vendor> vendors = dataCacheService.getDataListByType(Vendor.class);
		List<VendorSystem> vendorSystems = dataCacheService.getDataListByType(VendorSystem.class);
	
		if(vendors == null || vendors.size() == 0) 
			return null;
		
		for(Vendor vendor : vendors) {
			for(VendorSystem vs : vendorSystems) {
				if(vendor.getVendorSystemId().equals(vs.getId()))
					vendor.setVendorSystem(vs);
			}
		}
		
		return vendors;
	}

	@Override
	public void notifyBasicCacheRefresh() {
		System.out.println("cache updated!");
	}

}
