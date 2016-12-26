package com.tianyalan.product.core.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.dao.ProductDao;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.test.TestBase;
import com.tianyalan.product.mybatis.page.Page;

public class ProductDaoTest extends TestBase {

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAddProducts() {
		List<Product> products = new ArrayList<Product>();
		
		Product product = new Product();
		product.setProductCode("product1");
		product.setProductName("苹果一号");
		product.setPrice(10.0F);;
		product.setVendorId(1L);
		
		products.add(product);	
		
		productDao.addProducts(products);
	}
	
	@Test
	public void testGetProductByWildcard() {
		Page<Product> productPage = productDao.getProductByWildcard(1, 20, "苹果");				
		
		Assert.assertTrue(productPage.getPageNumber() == 1);
		Assert.assertTrue(productPage.getPageSize() == 20);
		Assert.assertTrue(productPage.getResult().size() == 2);
	}
	
	@Test
	public void testDeleteProducts() {
		Product product = productDao.getProductByCode("product1");
		Assert.assertTrue(product != null);
		Assert.assertTrue(product.getId() > 0);
		
		List<Long> productdIds = new ArrayList<Long>();
		productdIds.add(product.getId());
		
		productDao.deleteProducts(productdIds);
		
		product = productDao.getProductByCode("product1");
		Assert.assertTrue(product == null);
	}
}
