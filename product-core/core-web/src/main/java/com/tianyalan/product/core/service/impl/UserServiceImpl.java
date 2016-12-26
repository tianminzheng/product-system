package com.tianyalan.product.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.service.UserService;
import com.tianyalan.product.core.service.local.UserLocalService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserLocalService userLocalService;
	
	@Override
	public void addUser(User user) {
		userLocalService.addUser(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userLocalService.getUserByUserName(userName);
	}

	@Override
	public void updateUser(User user) {
		userLocalService.updateUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userLocalService.getUserById(id);
	}

}
