package com.tianyalan.product.core.service.local.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.core.dao.VendorDao;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.service.local.VendorLocalService;

@Service("vendorLocalService")
public class VendorLocalServiceImpl implements VendorLocalService {

	@Autowired
	private VendorDao vendorDao;
	
	@Override
	public Vendor getVendorByVendorName(String vendorName) {
		return vendorDao.getVendorByVendorName(vendorName);
	}

	@Override
	public VendorSystem getVendorSystemByVendorName(String vendorName) {
		Vendor vendor = getVendorByVendorName(vendorName);
		
		if(vendor == null)
			return null;

		return vendorDao.getVendorSystemById(vendor.getVendorSystemId());
	}

	@Override
	public Vendor getVendorById(Long id) {
		return vendorDao.getVendorById(id);
	}

	@Override
	public List<Vendor> getVendorsWithVendorSystem() {
		
		List<Vendor> vendors = vendorDao.getAllVendors();
		if(vendors == null || vendors.size() == 0) 
			return null;
		
		for(Vendor vendor : vendors) {
			VendorSystem vendorSystem = vendorDao.getVendorSystemById(vendor.getVendorSystemId());
			vendor.setVendorSystem(vendorSystem);
		}
		
		return vendors;
	}

	@Override
	public void updateVendor(Vendor vendor) {
		vendorDao.updateVendor(vendor);
	}

}
