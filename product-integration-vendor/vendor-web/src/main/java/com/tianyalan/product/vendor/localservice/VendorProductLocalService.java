package com.tianyalan.product.vendor.localservice;

import java.util.List;

import com.tianyalan.product.foundation.exception.ServiceException;
import com.tianyalan.product.vendor.domain.VendorProduct;

public interface VendorProductLocalService {

	List<VendorProduct> getVendorProductByTimeRange(String startTime, String endTime) throws ServiceException;
}
