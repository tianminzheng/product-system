package com.tianyalan.product.integration.route;

import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;

public interface RouteService {
	<T extends ResponseBase> T call(String requestCode, RequestBase requestBase);
	
}
