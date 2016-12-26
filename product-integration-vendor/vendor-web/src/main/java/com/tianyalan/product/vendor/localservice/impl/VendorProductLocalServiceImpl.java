package com.tianyalan.product.vendor.localservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.foundation.exception.ServiceException;
import com.tianyalan.product.vendor.dao.VendorProductDao;
import com.tianyalan.product.vendor.domain.VendorProduct;
import com.tianyalan.product.vendor.localservice.VendorProductLocalService;

@Service("vendorProductLocalService")
public class VendorProductLocalServiceImpl implements VendorProductLocalService {

	@Autowired
	private VendorProductDao vendorProductDao;
	
	@Override
	public List<VendorProduct> getVendorProductByTimeRange(String startTime,
			String endTime)  throws ServiceException {
		return vendorProductDao.getVendorProductByTimeRange(startTime, endTime);
	}

}
