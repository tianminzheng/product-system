package com.tianyalan.product.core.test.messaging;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.dao.cache.CacheService;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.message.AsyncProductSyncRequestService;
import com.tianyalan.product.core.test.TestBase;
import com.tianyalan.product.integration.request.ProductSyncRequest;

public class ProductSyncMessagingTest extends TestBase {

	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private AsyncProductSyncRequestService asyncProductSyncRequestService;

	@Test
	public void productSyncRequestProductTest() {
		List<Vendor> vendors = cacheService.getVendorsWithVendorSystem();

		for (Vendor vendor : vendors) {

			ProductSyncRequest request = new ProductSyncRequest();
			request.setVendorId(vendor.getId());

			VendorSystem vendorSystem = vendor.getVendorSystem();
			String vendorSystemAddress = vendorSystem.getIp() + ":" + vendorSystem.getPort();
			request.setVendorSystemAddress(vendorSystemAddress);

			request.setStartTime("2016-04-01");
			request.setEndTime("2016-04-30");

			asyncProductSyncRequestService.sendProductSyncRequest(request);
		}
	}

}
