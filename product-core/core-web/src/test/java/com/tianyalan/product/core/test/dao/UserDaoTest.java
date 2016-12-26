package com.tianyalan.product.core.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianyalan.product.core.dao.UserDao;
import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.test.TestBase;


public class UserDaoTest extends TestBase {

	@Autowired
	private UserDao userDao;
	
	private String userName = "tianyalan";
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUserName(userName);
		user.setPassword("1234560");
		user.setRealName("realName");
		
		userDao.addUser(user);	
		
		//验证数据库Id自动生成机制
		Assert.assertTrue(user.getId() > 0);
	}	

	//执行两次
	@Test
	public void testGetUserByUserName(){
		User user = userDao.getUserByUserName(userName);
		
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void testUpdateUser() {
		User user = userDao.getUserByUserName(userName);		
		user.setPassword("newPassword");
		userDao.updateUser(user);
		
		User updatedUser = userDao.getUserByUserName(userName);

		Assert.assertTrue(updatedUser.getPassword().equals("newPassword"));
	}
			
}
