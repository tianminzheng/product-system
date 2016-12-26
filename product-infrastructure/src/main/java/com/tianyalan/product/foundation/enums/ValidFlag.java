package com.tianyalan.product.foundation.enums;

public enum ValidFlag {
	ENABLE,
	DISABLE;
	
	public String getChineseStr(){
	    switch (this) {
            case ENABLE:
                return "有效";
            case DISABLE:
                return "无效";
            default:
                return "未知";
        }
	}
}
