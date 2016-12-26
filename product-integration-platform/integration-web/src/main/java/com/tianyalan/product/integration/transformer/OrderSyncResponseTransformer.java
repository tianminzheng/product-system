package com.tianyalan.product.integration.transformer;

import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;

public class OrderSyncResponseTransformer implements ResponseTransformer {

	@Override
	public ResponseBase transfrom(RequestBase request, Object remoteResponse) {
		
		return (ResponseBase)remoteResponse;
	}

}
