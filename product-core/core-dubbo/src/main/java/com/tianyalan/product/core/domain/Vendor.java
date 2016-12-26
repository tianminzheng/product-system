package com.tianyalan.product.core.domain;

import com.tianyalan.product.foundation.entity.BaseEntity;

public class Vendor extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String vendorName;
	
	private String description;
	
	private Long addressId;
	
	private Address address;

	private Long vendorSystemId; 
	
	private VendorSystem vendorSystem;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getVendorSystemId() {
		return vendorSystemId;
	}

	public void setVendorSystemId(Long vendorSystemId) {
		this.vendorSystemId = vendorSystemId;
	}

	public VendorSystem getVendorSystem() {
		return vendorSystem;
	}

	public void setVendorSystem(VendorSystem vendorSystem) {
		this.vendorSystem = vendorSystem;
	}
}
