package com.tianyalan.product.core.test.cache;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.product.core.dao.cache.CacheService;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.test.TestBase;

public class CacheTest extends TestBase {

	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testGetVendorsFromCache() throws InterruptedException {
		List<Vendor> vendors = cacheService.getVendors();
		
		Assert.isTrue(vendors.size() > 0);
		System.out.println(vendors.get(0).getVendorName());
		
		Thread.sleep(10000);
		
		List<Vendor> vendors2 = cacheService.getVendors();
		System.out.println(vendors2.get(0).getVendorName());
		
	}
	
	@Test
	public void testGetVendorsWithVendorSystemFromCache() throws InterruptedException {
		List<Vendor> vendors = cacheService.getVendorsWithVendorSystem();
		
		Assert.isTrue(vendors.size() > 0);
		Assert.isTrue(vendors.get(0).getVendorSystem() != null);
		
		System.out.println(vendors.get(0).getVendorSystem().getIp());
		
	}
}
