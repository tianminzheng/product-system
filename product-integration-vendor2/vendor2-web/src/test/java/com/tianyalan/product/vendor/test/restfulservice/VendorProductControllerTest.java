package com.tianyalan.product.vendor.test.restfulservice;

import com.tianyalan.product.foundation.util.ResultMessageBuilder.ResultMessage;
import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.vendor.test.RestTemplateUtil;

public class VendorProductControllerTest {

	public static void main(String[] args) {
		testGetProducts();
	}

	private static void testGetProducts() {

		ProductSyncRequest request = new ProductSyncRequest();
		request.setStartTime("2016-4-1");
		request.setEndTime("2016-4-30");
		ResultMessage result = RestTemplateUtil.postForObject(
				"http://127.0.0.1:8081/vendor2-web/product", request);
		
//		ResultMessage result = RestTemplateUtil.postForObject(
//				"http://127.0.0.1:8083/vendor-web/product", request);

		System.out.println(result.getErrMsg());
		System.out.println(result.getData());
	}
}
