package com.tianyalan.product.core.test.integration.cache;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.dao.cache.CacheService;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.domain.Vendor;
import com.tianyalan.product.core.domain.VendorSystem;
import com.tianyalan.product.core.service.local.ProductLocalService;
import com.tianyalan.product.core.test.TestBase;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductItem;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.service.ProductSyncService;

public class ProductSyncWithCacheTest extends TestBase {

	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private ProductLocalService productLocalService;	
	
	@Autowired
	private ProductSyncService productSyncService;
	
	@Test
	public void testProductSync(){
		
		List<Vendor> vendors = cacheService.getVendorsWithVendorSystem();
		
		for(Vendor vendor : vendors) {

			ProductSyncRequest request = new ProductSyncRequest();
			request.setVendorId(vendor.getId());
			
			VendorSystem vendorSystem = vendor.getVendorSystem();
			String vendorSystemAddress = vendorSystem.getIp() + ":" + vendorSystem.getPort();
			request.setVendorSystemAddress(vendorSystemAddress);
			
			request.setStartTime("2016-04-01");
			request.setEndTime("2016-04-30");
			
			ProductSyncResponse response = productSyncService.productSync(request);
			List<ProductItem> productItems = response.getProductItems();
			
			List<Product> products = convertProduct(productItems);
			productLocalService.addProducts(products);
		}
	}
	
	private List<Product> convertProduct(List<ProductItem> productItems) {
		List<Product> products = new ArrayList<Product>();
		
		for(ProductItem item : productItems) {
			Product product = new Product();
			product.setVendorId(item.getVendorId());
			product.setProductCode(item.getProductCode());
			product.setProductName(item.getProductName());
			product.setDescription(item.getDescription());
			product.setPrice(item.getPrice());
			products.add(product);
		}
		
		return products;
	}
}