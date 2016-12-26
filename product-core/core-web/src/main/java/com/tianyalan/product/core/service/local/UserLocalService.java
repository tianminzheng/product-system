package com.tianyalan.product.core.service.local;

import com.tianyalan.product.core.domain.User;
import com.tianyalan.product.foundation.exception.ServiceException;

public interface UserLocalService {
	
	void addUser(User user) throws ServiceException;
	
	User getUserByUserName(String userName);
	
	void updateUser(User user);

	User getUserById(Long id);
}
