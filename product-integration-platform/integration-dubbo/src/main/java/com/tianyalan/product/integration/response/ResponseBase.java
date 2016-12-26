package com.tianyalan.product.integration.response;

import java.io.Serializable;

public class ResponseBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode; 
	private String errorMsg; 
	private String requestNO;
	private String jsonResponse;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRequestNO() {
		return requestNO;
	}
	public void setRequestNO(String requestNO) {
		this.requestNO = requestNO;
	}	
	public String getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	public boolean isSuccess() {
		return true;
	}
}
