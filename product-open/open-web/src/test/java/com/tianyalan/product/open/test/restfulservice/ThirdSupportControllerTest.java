package com.tianyalan.product.open.test.restfulservice;

import com.tianyalan.product.foundation.util.ResultMessageBuilder.ResultMessage;
import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.test.RestTemplateUtil;

public class ThirdSupportControllerTest {

	public static void main(String[] args) {
		testAddThirdSupport();
	}
	
	private static void testAddThirdSupport() {

		ThirdSupport thirdSupport = new ThirdSupport();
		thirdSupport.setOrganization("tianyalan3");
		thirdSupport.setAccessSecretKey("tianyalan_secret_key");
		thirdSupport.setAccessName("tianyalan");
		thirdSupport.setAccessIp("127.0.0.1");

		ResultMessage result = RestTemplateUtil.postForObject(
				"http://127.0.0.1:8081/open-web/access", thirdSupport);

		System.out.println(result.getErrMsg());
	}

}
