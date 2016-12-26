package com.tianyalan.product.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.service.ProductService;
import com.tianyalan.product.core.service.local.ProductLocalService;
import com.tianyalan.product.mybatis.page.Page;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductLocalService productLocalService;
	
	@Override
	public void addProducts(List<Product> products) {
		productLocalService.addProducts(products);
	}

	@Override
	public Page<Product> getProductByWildcard(int pageNum, int pageSize, String wildcard) {
		return productLocalService.getProductByWildcard(pageNum, pageSize, wildcard);
	}

	@Override
	public Product getProductByCode(String productCode) {
		return productLocalService.getProductByCode(productCode);
	}

	@Override
	public void deleteProducts(List<Product> products) {
		productLocalService.deleteProducts(products);
	}
}
