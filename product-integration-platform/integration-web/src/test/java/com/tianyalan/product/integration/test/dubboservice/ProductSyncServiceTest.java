package com.tianyalan.product.integration.test.dubboservice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.service.ProductSyncService;
import com.tianyalan.product.integration.test.TestBase;

public class ProductSyncServiceTest extends TestBase {

	@Autowired
	private ProductSyncService productSyncService;
	
	@Test
	public void testProductSync() {		
		ProductSyncRequest request = new ProductSyncRequest();
		request.setVendorId(1L);
		request.setVendorSystemAddress("127.0.0.1:8082");
		request.setStartTime("2016-4-1");
		request.setEndTime("2016-4-30");
		
		ProductSyncResponse response = productSyncService.productSync(request);
		Assert.isTrue(response.getProductItems().size() > 0);
		
		System.out.print(response.getJsonResponse());		
	}

}
