package com.tianyalan.product.core.dao;

import java.util.List;

import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.mybatis.page.Page;

public interface ProductDao {

	void addProducts(List<Product> products);
	
	Page<Product> getProductByWildcard(int pageNum, int pageSize, String wildcard);
	
	Product getProductByCode(String productCode);
	
	void deleteProducts(List<Long> productIds);
}
