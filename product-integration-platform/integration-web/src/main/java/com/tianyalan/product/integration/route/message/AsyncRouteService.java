package com.tianyalan.product.integration.route.message;

import com.tianyalan.product.integration.request.RequestBase;

public interface AsyncRouteService {
	void call(String requestCode, RequestBase requestBase);
	
}
