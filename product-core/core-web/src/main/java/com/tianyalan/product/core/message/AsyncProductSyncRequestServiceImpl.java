package com.tianyalan.product.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.integration.request.ProductSyncRequest;

@Service("asyncProductSyncRequestService")
public class AsyncProductSyncRequestServiceImpl implements AsyncProductSyncRequestService {

	@Autowired
	private JmsTemplateUtil productSyncRequestProducer;
	
	public void sendProductSyncRequest(ProductSyncRequest request) {
		
		productSyncRequestProducer.sendProductSyncRequestMessage(request);
	}
}
