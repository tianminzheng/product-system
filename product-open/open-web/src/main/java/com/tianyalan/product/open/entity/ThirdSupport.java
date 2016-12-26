package com.tianyalan.product.open.entity;

import com.tianyalan.product.foundation.entity.BaseEntity;

public class ThirdSupport extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 第三方组织
	 */
	private String organization;

	/**
	 * 第三方访问名称
	 */
	private String accessName;

	/**
	 * 第三方鉴权key，系统自动生成
	 */
	private String accessKey;

	/**
	 * 第三方鉴权密钥，第三方填写
	 */
	private String accessSecretKey;

	/**
	 * 接入方真实IP，允许提供多个IP，以逗号隔开
	 */
	private String accessIp;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessSecretKey() {
		return accessSecretKey;
	}

	public void setAccessSecretKey(String accessSecretKey) {
		this.accessSecretKey = accessSecretKey;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

}
