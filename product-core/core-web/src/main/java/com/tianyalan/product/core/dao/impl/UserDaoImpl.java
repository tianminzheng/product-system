package com.tianyalan.product.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.core.dao.UserDao;
import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addUser(User user) {
		myBatisDAO.insert("addUser", user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return (User)myBatisDAO.findForObject("getUserByUserName", userName);
	}

	@Override
	public void updateUser(User user) {
		myBatisDAO.update("updateUser", user);
	}

	@Override
	public User getUserById(Long id) {
		return (User)myBatisDAO.findForObject("getUserById", id);
	}

}
