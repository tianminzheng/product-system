package com.tianyalan.product.vendor.dao;

import java.util.List;

import com.tianyalan.product.vendor.domain.VendorProduct;

public interface VendorProductDao {

	List<VendorProduct> getVendorProductByTimeRange(String startTime, String endTime);
}
