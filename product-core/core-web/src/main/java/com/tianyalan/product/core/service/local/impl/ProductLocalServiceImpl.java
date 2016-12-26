package com.tianyalan.product.core.service.local.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.core.dao.ProductDao;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.service.local.ProductLocalService;
import com.tianyalan.product.mybatis.page.Page;

@Service("productLocalService")
public class ProductLocalServiceImpl implements ProductLocalService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public void addProducts(List<Product> products) {
		productDao.addProducts(products);
	}

	@Override
	public Page<Product> getProductByWildcard(int pageNum, int pageSize, String wildcard) {
		return productDao.getProductByWildcard(pageNum, pageSize, wildcard);
	}

	@Override
	public Product getProductByCode(String productCode) {
		return productDao.getProductByCode(productCode);
	}

	@Override
	public void deleteProducts(List<Product> products) {
		List<Long> productIds = new ArrayList<Long>();
		for(Product product : products) {
			productIds.add(product.getId());
		}
		
		productDao.deleteProducts(productIds);
	}
}
