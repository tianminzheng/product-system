package com.tianyalan.product.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;
import com.tianyalan.product.integration.route.RouteService;

public abstract class AbstractRequestService {
	
	@Autowired
	private RouteService routeService;
	
	protected <T extends ResponseBase> T sendRequest(String requestCode, RequestBase requestBase){
		
		return routeService.call(requestCode, requestBase);
	}
}
