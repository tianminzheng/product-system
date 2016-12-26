package com.tianyalan.product.core.domain;

import com.tianyalan.product.foundation.entity.BaseEntity;

public class Product extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String productCode;
	
	private String productName;
	
	private String description;

	private Long vendorId;
	
	private Float price;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
