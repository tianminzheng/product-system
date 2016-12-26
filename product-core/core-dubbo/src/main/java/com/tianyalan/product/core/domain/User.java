package com.tianyalan.product.core.domain;

import com.tianyalan.product.foundation.entity.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String password;
	
	private String realName;
	
	private Long addressId;
	
	private Address address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
