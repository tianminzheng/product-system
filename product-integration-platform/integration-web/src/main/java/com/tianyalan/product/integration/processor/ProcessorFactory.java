package com.tianyalan.product.integration.processor;

import com.tianyalan.product.integration.config.RequestCode;

public class ProcessorFactory {

	public static RequestProcessor getProcessor(String requestCode) {
		if(requestCode.equals(RequestCode.PRODUCT_SYNC)) {
			return new ProductSyncProcessor();
		} else {
			return new OrderSyncProcessor();
		}		
	}
}
