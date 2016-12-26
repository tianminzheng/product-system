package com.tianyalan.product.integration.route;

import org.springframework.stereotype.Service;

import com.tianyalan.product.integration.processor.ProcessorFactory;
import com.tianyalan.product.integration.processor.RequestProcessor;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ResponseBase;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

	@Override
	public <T extends ResponseBase> T call(String requestCode,
			RequestBase requestBase) {
		
		RequestProcessor processor = ProcessorFactory.getProcessor(requestCode);

		T responseBase = (T) processor.process(requestBase);

		return responseBase;
	}
}
