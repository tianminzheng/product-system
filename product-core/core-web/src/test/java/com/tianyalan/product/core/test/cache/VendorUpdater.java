package com.tianyalan.product.core.test.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.service.local.VendorLocalService;
import com.tianyalan.product.core.test.TestBase;

public class VendorUpdater extends TestBase {

	@Autowired
	private VendorLocalService vendorLocalService;
	
	@Test
	public void updateVendor() {
		Vendor vendor = new Vendor();
		vendor.setId(1L);
		vendor.setVendorName("New_vendor");
		
		vendorLocalService.updateVendor(vendor);
	}

}
