package com.tianyalan.product.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.core.dao.ProductDao;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;
import com.tianyalan.product.mybatis.page.Page;
import com.tianyalan.product.mybatis.page.PageRequest;

@Repository("productDao")
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addProducts(List<Product> products) {
		myBatisDAO.insert("addProducts", products);
	}

	@Override
	public Page<Product> getProductByWildcard(int pageNum, int pageSize, String wildcard) {
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("wildcard", wildcard);
		return myBatisDAO.findForPage("getProductByWildcard", new PageRequest(pageNum, pageSize, filter));
	}

	@Override
	public Product getProductByCode(String productCode) {
		return (Product)myBatisDAO.findForObject("getProductByCode", productCode);
	}

	@Override
	public void deleteProducts(List<Long> productIds) {
		myBatisDAO.delete("deleteProducts", productIds);
	}

}
