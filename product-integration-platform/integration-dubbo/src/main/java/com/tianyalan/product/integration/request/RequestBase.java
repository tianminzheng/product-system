package com.tianyalan.product.integration.request;

import java.io.Serializable;

public class RequestBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long vendorId;
	private String vendorSystemAddress; //IP:Port
	private String requestNO;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getVendorSystemAddress() {
		return vendorSystemAddress;
	}

	public void setVendorSystemAddress(String vendorSystemAddress) {
		this.vendorSystemAddress = vendorSystemAddress;
	}

	public String getRequestNO() {
		return requestNO;
	}

	public void setRequestNO(String requestNO) {
		this.requestNO = requestNO;
	}
}
