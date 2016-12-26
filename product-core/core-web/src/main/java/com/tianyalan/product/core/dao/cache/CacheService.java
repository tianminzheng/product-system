package com.tianyalan.product.core.dao.cache;

import java.util.List;

import com.tianyalan.product.core.domain.Vendor;

public interface CacheService {

	List<Vendor> getVendors();
	
	List<Vendor> getVendorsWithVendorSystem();
	
}
