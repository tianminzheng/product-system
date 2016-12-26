package com.tianyalan.product.core.service;

import java.util.List;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;

public interface VendorService {
	
	Vendor getVendorById(Long id);

	Vendor getVendorByVendorName(String vendorName);
	
	VendorSystem getVendorSystemByVendorName(String vendorName);
	
	List<Vendor> getVendorsWithVendorSystem();
}
