package com.tianyalan.product.foundation.entity;

import java.io.Serializable;
import java.util.Date;

import com.tianyalan.product.foundation.enums.ValidFlag;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; // 主键
	
	private Date createTime; // 创建时间
	
	private Date updateTime; // 更新时间
	
	private ValidFlag validFlag; // 有效性

	public BaseEntity(){}
	
	public BaseEntity(Long id){
	    this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ValidFlag getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(ValidFlag validFlag) {
		this.validFlag = validFlag;
	}
	
}
