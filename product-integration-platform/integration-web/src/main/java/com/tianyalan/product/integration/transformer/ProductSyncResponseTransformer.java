package com.tianyalan.product.integration.transformer;

import com.alibaba.fastjson.JSON;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ProductItem;
import com.tianyalan.product.integration.response.ProductSyncResponse;
import com.tianyalan.product.integration.response.ResponseBase;

public class ProductSyncResponseTransformer implements ResponseTransformer {

	@Override
	public ResponseBase transfrom(RequestBase request, Object remoteResponse) {
		ResponseBase responseBase = (ResponseBase)remoteResponse;
		
		ProductSyncResponse response = new ProductSyncResponse();
		response.setErrorCode(responseBase.getErrorCode());
		response.setErrorMsg(responseBase.getErrorMsg());
		response.setRequestNO(responseBase.getRequestNO());
		response.setJsonResponse(responseBase.getJsonResponse());
				
		response.setProductItems(JSON.parseArray(responseBase.getJsonResponse(), ProductItem.class));
		
		return response;
	}

}
