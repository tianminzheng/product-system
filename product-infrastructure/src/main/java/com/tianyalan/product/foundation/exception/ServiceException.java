package com.tianyalan.product.foundation.exception;

public class ServiceException extends NestedRuntimeException{
	
	private static final long serialVersionUID = 200010100L;
	
	private String serviceMsg;
	
	public ServiceException() {
        super();
    }
	
	/**
	 * Construct a <code>NestedRuntimeException</code> with the specified detail message.
	 * @param msg the detail message
	 */
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(String msg,String serviceMsg) {
		super(msg);
		this.serviceMsg = serviceMsg;
	}

	/**
	 * Construct a <code>NestedRuntimeException</code> with the specified detail message
	 * and nested exception.
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
