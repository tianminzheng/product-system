package com.tianyalan.product.core.message;

import com.tianyalan.product.integration.request.ProductSyncRequest;

public interface AsyncProductSyncRequestService {
	
	public void sendProductSyncRequest(ProductSyncRequest request);
}
