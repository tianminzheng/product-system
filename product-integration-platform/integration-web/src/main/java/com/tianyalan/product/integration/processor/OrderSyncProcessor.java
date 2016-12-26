package com.tianyalan.product.integration.processor;

import com.tianyalan.product.integration.config.RequestCode;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.OrderSyncResponse;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.response.ResponseBase;
import com.tianyalan.product.integration.transformer.ResponseTransformer;
import com.tianyalan.product.integration.transformer.ResponseTransformerFactory;

public class OrderSyncProcessor extends RequestProcessor {

	@Override
	public ResponseBase transformResponse(RequestBase pRequest, ResponseBase originalResponse) {
		ResponseTransformer transformer = ResponseTransformerFactory.getTransformer(RequestCode.PRODUCT_SYNC);
		ResponseBase response = transformer.transfrom(pRequest, originalResponse);
		
		return filterResponse(pRequest, response);
	}
	
	private ResponseBase filterResponse(RequestBase pRequest, ResponseBase pResponse) {
		OrderSyncResponse orderSyncResponse = (OrderSyncResponse)pResponse;
		
		//可以添加对orderSyncResponse内容过滤操作
		return orderSyncResponse;
	}

	@Override
	public String getTargetRequestPath() {
		return "order";
	}
}
