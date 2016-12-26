package com.tianyalan.product.open.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.open.dao.ThirdSupportDao;
import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.test.TestBase;

public class ThirdSupportDaoTest extends TestBase {

	@Autowired
	private ThirdSupportDao thirdSupportDao;
	
	@Test
	public void testAddThirdSupport() {
		ThirdSupport thirdSupport = new ThirdSupport();
		thirdSupport.setOrganization("tianyalan1");
		thirdSupport.setAccessKey("tianyalan_key");
		thirdSupport.setAccessSecretKey("tianyalan_secret_key");
		thirdSupport.setAccessName("tianyalan");
		thirdSupport.setAccessIp("127.0.0.1");
		
		thirdSupportDao.addThirdSupport(thirdSupport);
		
		Assert.assertTrue(thirdSupport.getId() > 0);
	}
}
