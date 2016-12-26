package com.tianyalan.product.foundation.enums;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WSSexType")
public enum SexType {
	
	MALE("男"), FEMALE("女"), OTHERS("未知");
	
	String chineseStr;

	private SexType(String chineseStr) {

		this.chineseStr = chineseStr;
	}

	public String getChineseStr() {
		return chineseStr;
	}
}
