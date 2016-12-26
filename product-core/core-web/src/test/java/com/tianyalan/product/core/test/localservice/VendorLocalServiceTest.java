package com.tianyalan.product.core.test.localservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.service.local.VendorLocalService;
import com.tianyalan.product.core.test.TestBase;

public class VendorLocalServiceTest extends TestBase {

	@Autowired
	private VendorLocalService vendorLocalService;
	
	@Test
	public void testGetVendorSystemByVendorName() {
		VendorSystem vendorSystem = vendorLocalService.getVendorSystemByVendorName("testVendor");
		
		Assert.assertTrue(vendorSystem == null);
	}
}
