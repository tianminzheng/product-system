package com.tianyalan.product.core.service.local.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.core.dao.UserDao;
import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.core.service.local.UserLocalService;
import com.tianyalan.product.foundation.exception.ServiceException;

@Service("userLocalService")
public class UserLocalServiceImpl implements UserLocalService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) throws ServiceException {
		User existedUser = userDao.getUserByUserName(user.getUserName());
		if(existedUser != null) {
			throw new ServiceException("已存在相同用户名的用户！");
		}
		
		userDao.addUser(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

}
