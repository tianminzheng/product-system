package com.tianyalan.product.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.service.VendorService;
import com.tianyalan.product.core.service.local.VendorLocalService;

public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorLocalService vendorLocalService;
	
	@Override
	public Vendor getVendorByVendorName(String vendorName) {
		return vendorLocalService.getVendorByVendorName(vendorName);
	}

	@Override
	public VendorSystem getVendorSystemByVendorName(String vendorName) {
		return vendorLocalService.getVendorSystemByVendorName(vendorName);
	}

	@Override
	public Vendor getVendorById(Long id) {
		return vendorLocalService.getVendorById(id);
	}

	@Override
	public List<Vendor> getVendorsWithVendorSystem() {
		return vendorLocalService.getVendorsWithVendorSystem();
	}

}
