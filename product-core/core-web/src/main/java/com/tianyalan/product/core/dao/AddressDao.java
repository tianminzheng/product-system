package com.tianyalan.product.core.dao;

import com.tianyalan.product.core.domain.Address;

public interface AddressDao {

	void addAddress(Address address);
	
	Address getAddressById(Long id);
}
