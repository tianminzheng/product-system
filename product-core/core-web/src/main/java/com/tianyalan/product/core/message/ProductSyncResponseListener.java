package com.tianyalan.product.core.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.cache.config.LocalCacheDefinition;
import com.tianyalan.product.core.domain.Product;
import com.tianyalan.product.core.service.local.ProductLocalService;
import com.tianyalan.product.integration.response.ProductItem;
import com.tianyalan.product.integration.response.ProductSyncResponse;

@Component("productSyncResponseListener")
public class ProductSyncResponseListener implements MessageListener {

	protected static final Logger logger = LoggerFactory.getLogger("PRODUCT_SYNC_LOGGER");
	
	
	private ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1); 
	
	@Autowired
	private ProductLocalService productLocalService;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;		
		try {
			String jsonStr = textMessage.getText();
			System.out.println("[ProductSyncResponseListener.onMessage]:receive message is,"+ jsonStr);
			
			if (jsonStr != null) {				
				ProductSyncResponse productSyncResponse = JSON.parseObject(jsonStr, ProductSyncResponse.class);
				
				List<ProductItem> productItems = productSyncResponse.getProductItems();
				
				List<Product> products = convertProduct(productItems);
				productLocalService.addProducts(products);
				
//				AddProductsWorker worker = new AddProductsWorker(products);
//				ex.execute(worker);	
			}
		} catch (JMSException e) {
			logger.error("[ProductSyncResponseListener.onMessage]:receive message occured an exception",e);
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
	
	private class AddProductsWorker implements Runnable{
		private List<Product> products;
		
		AddProductsWorker(List<Product> products){
			this.products = products;
		}
		@Override
		public void run() {
			productLocalService.addProducts(products);
			System.out.println("[ProductSyncResponseListener.onMessage]:add products success!");
		}		
	}
}
