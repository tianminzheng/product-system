package com.tianyalan.product.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.core.dao.AddressDao;
import com.tianyalan.product.core.domain.Address;
import com.tianyalan.product.mybatis.dao.MyBatisDAO;

@Repository("addressDao")
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	@Override
	public void addAddress(Address address) {
		myBatisDAO.insert("addAddress", address);
	}

	@Override
	public Address getAddressById(Long id) {
		return (Address)myBatisDAO.findForObject("getAddressById", id);
	}
}
