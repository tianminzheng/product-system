package com.tianyalan.product.integration.service.impl;

import com.tianyalan.product.integration.config.RequestCode;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.service.ProductSyncService;

public class ProductSyncServiceImpl extends AbstractRequestService implements ProductSyncService{

	@Override
	public ProductSyncResponse productSync(ProductSyncRequest request) {
		ProductSyncResponse response = sendRequest(RequestCode.PRODUCT_SYNC, request);
		
		return response;
	}
}
