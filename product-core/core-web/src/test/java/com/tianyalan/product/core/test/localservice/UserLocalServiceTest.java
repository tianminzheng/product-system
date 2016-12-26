package com.tianyalan.product.core.test.localservice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.service.local.UserLocalService;
import com.tianyalan.product.core.test.TestBase;
import com.tianyalan.product.foundation.exception.ServiceException;

public class UserLocalServiceTest extends TestBase {

	@Autowired
	private UserLocalService userLocalService;
	
	@Test(expected = ServiceException.class)
	public void testAddUser(){
		User user = new User();
		user.setUserName("tianyalan");
		user.setPassword("1234560");
		user.setRealName("realName");
		
		userLocalService.addUser(user);	
	}
}
