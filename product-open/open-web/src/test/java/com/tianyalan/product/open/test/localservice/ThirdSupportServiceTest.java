package com.tianyalan.product.open.test.localservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.service.local.ThirdSupportService;
import com.tianyalan.product.open.test.TestBase;

public class ThirdSupportServiceTest extends TestBase {

	@Autowired
	private ThirdSupportService thirdSupportService;
	
	@Test
	public void testAddThirdSupport() {
		ThirdSupport thirdSupport = new ThirdSupport();
		thirdSupport.setOrganization("tianyalan2");
		thirdSupport.setAccessKey("tianyalan_key");
		thirdSupport.setAccessSecretKey("tianyalan_secret_key");
		thirdSupport.setAccessName("tianyalan");
		thirdSupport.setAccessIp("127.0.0.1");
		
		thirdSupportService.addThirdSupport(thirdSupport);
		
		Assert.assertTrue(thirdSupport.getId() > 0);
	}
}
