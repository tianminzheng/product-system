package com.tianyalan.product.core.test.dubboservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.service.UserService;
import com.tianyalan.product.core.test.TestBase;

public class UserServiceTest extends TestBase {
	
	@Autowired
	private UserService dubboUserService;
	
	@Test
	public void testAddUser(){
		User user = dubboUserService.getUserByUserName("tianyalan");
		
		Assert.assertTrue(user != null);
		Assert.assertTrue(user.getUserName().equals("tianyalan"));
	}

}
