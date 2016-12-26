package com.tianyalan.product.open.test.restfulservice;

import com.tianyalan.product.foundation.util.ResultMessageBuilder.ResultMessage;
import com.tianyalan.product.open.test.RestTemplateUtil;

public class OpenOrderControllerTest {

	public static void main(String[] args) {
		testOpenGetOrderById();
	}
	
	private static void testOpenGetOrderById() {

		ResultMessage result = RestTemplateUtil.postForObjectWithPathVariable(
				"http://127.0.0.1:8081/open-web/open/order/{orderId}",
				null, 1L);

		System.out.println(result.getErrMsg());
	}
}
