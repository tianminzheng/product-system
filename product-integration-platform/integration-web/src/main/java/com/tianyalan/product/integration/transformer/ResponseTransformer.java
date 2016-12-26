package com.tianyalan.product.integration.transformer;

import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;

public interface ResponseTransformer {
	
	ResponseBase transfrom(RequestBase request,Object remoteResponse);
	
}
