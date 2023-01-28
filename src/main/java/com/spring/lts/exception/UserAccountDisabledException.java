package com.spring.lts.exception;

import java.util.UUID;

public class UserAccountDisabledException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Object optionalData;
	private Throwable errorData;
	final private UUID transactionId = UUID.randomUUID();
	
	public UserAccountDisabledException() {
		
	}
	
	public UserAccountDisabledException(Throwable errorData) {
		this.errorData = errorData;
	}
	
	public UserAccountDisabledException(Throwable errorData, Object optionalData) {
		this.errorData = errorData;
		this.optionalData = optionalData;
	}

	public Throwable getErrorData() {
		return errorData;
	}

	public void setErrorData(Throwable errorData) {
		this.errorData = errorData;
	}

	public UUID getTransactionId() {
		return transactionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getOptionalData() {
		return optionalData;
	}

	public void setOptionalData(Object optionalData) {
		this.optionalData = optionalData;
	}
}
