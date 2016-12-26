package com.tianyalan.product.core.dao;

import com.tianyalan.product.core.domain.User;

public interface UserDao {

	void addUser(User user);
	
	User getUserByUserName(String userName);
	
	void updateUser(User user);	

	User getUserById(Long id);
}
