package com.tianyalan.product.core.service.local;

import java.util.List;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;

public interface VendorLocalService {
	
	Vendor getVendorById(Long id);

	Vendor getVendorByVendorName(String vendorName);
	
	VendorSystem getVendorSystemByVendorName(String vendorName);
	
	//For cache
	List<Vendor> getVendorsWithVendorSystem();
	
	void updateVendor(Vendor vendor);
}
