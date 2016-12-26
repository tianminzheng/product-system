package com.tianyalan.product.core.service;

import com.tianyalan.product.core.domain.User;

public interface UserService {
	
	void addUser(User user);
	
	User getUserById(Long id);
	
	User getUserByUserName(String userName);
	
	void updateUser(User user);

}
