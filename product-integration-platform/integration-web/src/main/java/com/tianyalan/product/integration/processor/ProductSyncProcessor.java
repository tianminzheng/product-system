package com.tianyalan.product.integration.processor;

import com.tianyalan.product.integration.config.RequestCode;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.response.ResponseBase;
import com.tianyalan.product.integration.transformer.ResponseTransformer;
import com.tianyalan.product.integration.transformer.ResponseTransformerFactory;

public class ProductSyncProcessor extends RequestProcessor {

	@Override
	public ResponseBase transformResponse(RequestBase pRequest, ResponseBase originalResponse) {
		ResponseTransformer transformer = ResponseTransformerFactory.getTransformer(RequestCode.PRODUCT_SYNC);
		ResponseBase response = transformer.transfrom(pRequest, originalResponse);

		return filterResponse(pRequest, response);
	}

	private ResponseBase filterResponse(RequestBase pRequest, ResponseBase pResponse) {
		ProductSyncResponse productSyncResponse = (ProductSyncResponse)pResponse;
		
		//根据需要对productSyncResponse内容过滤操作
		return productSyncResponse;
	}

	@Override
	public String getTargetRequestPath() {
		return "product";
	}
}
