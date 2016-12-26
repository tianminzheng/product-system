package com.tianyalan.product.integration.request;

public class ProductSyncRequest extends RequestBase {
	
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
