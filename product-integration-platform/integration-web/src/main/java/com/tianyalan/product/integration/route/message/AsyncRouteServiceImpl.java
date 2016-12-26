package com.tianyalan.product.integration.route.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.integration.processor.ProcessorFactory;
import com.tianyalan.product.integration.processor.RequestProcessor;
import com.tianyalan.product.integration.request.RequestBase;
import com.tianyalan.product.integration.response.ProductSyncResponse;

@Service("asyncRouteService")
public class AsyncRouteServiceImpl implements AsyncRouteService {

	@Autowired
	private JmsTemplateUtil productSyncResponseProducer;
	
	@Override
	public void call(String requestCode, RequestBase requestBase) {
		RequestProcessor processor = ProcessorFactory.getProcessor(requestCode);

		ProductSyncResponse productSyncResponse = (ProductSyncResponse) processor.process(requestBase);

		productSyncResponseProducer.sendProductSyncResponseMessage(productSyncResponse);
	}

}
