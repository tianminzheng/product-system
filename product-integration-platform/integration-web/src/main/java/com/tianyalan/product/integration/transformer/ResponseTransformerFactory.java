package com.tianyalan.product.integration.transformer;

import com.tianyalan.product.integration.config.RequestCode;

public class ResponseTransformerFactory {

	public static ResponseTransformer getTransformer(String requestCode){
		if(requestCode.equals(RequestCode.PRODUCT_SYNC)) {
			return new ProductSyncResponseTransformer();
		} else {
			return new OrderSyncResponseTransformer();
		}		
	}
}
