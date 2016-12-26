package com.tianyalan.product.core.dao;

import java.util.List;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;

public interface VendorDao {

	Vendor getVendorByVendorName(String vendorName);
	
	VendorSystem getVendorSystemById(Long id);
	
	Vendor getVendorById(Long id);
	
	List<Vendor> getAllVendors();
	
	void updateVendor(Vendor vendor);
}
