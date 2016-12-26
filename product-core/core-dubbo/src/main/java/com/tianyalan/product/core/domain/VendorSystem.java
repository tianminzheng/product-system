package com.tianyalan.product.core.domain;

import java.io.Serializable;

public class VendorSystem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String ip;
	
	private String port;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
