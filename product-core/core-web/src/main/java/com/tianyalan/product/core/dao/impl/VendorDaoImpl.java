package com.tianyalan.product.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.core.dao.VendorDao;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

@Repository("vendorDao")
public class VendorDaoImpl implements VendorDao {
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public Vendor getVendorByVendorName(String vendorName) {
		return (Vendor)myBatisDAO.findForObject("getVendorByVendorName", vendorName);
	}

	@Override
	public VendorSystem getVendorSystemById(Long id) {
		return (VendorSystem)myBatisDAO.findForObject("getVendorSystemById", id);
	}

	@Override
	public Vendor getVendorById(Long id) {
		return (Vendor)myBatisDAO.findForObject("getVendorById", id);
	}

	@Override
	public List<Vendor> getAllVendors() {
		return (List<Vendor>)myBatisDAO.findForList("getAllVendors");
	}

	@Override
	public void updateVendor(Vendor vendor) {
		myBatisDAO.update("updateVendor", vendor);
	}
}
