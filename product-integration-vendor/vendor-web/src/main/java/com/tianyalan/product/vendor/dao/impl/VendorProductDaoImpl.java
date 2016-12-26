package com.tianyalan.product.vendor.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.mybatis.dao.MyBatisDAO;
import com.tianyalan.product.vendor.dao.VendorProductDao;
import com.tianyalan.product.vendor.domain.VendorProduct;

@SuppressWarnings("unchecked")
@Repository("productDao")
public class VendorProductDaoImpl implements VendorProductDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public List<VendorProduct> getVendorProductByTimeRange(String startTime,
			String endTime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		return (List<VendorProduct>)myBatisDAO.findForList("getVendorProductsByTimeRange", map);
	}

}
