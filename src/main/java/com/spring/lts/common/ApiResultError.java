package com.spring.lts.common;

public class ApiResultError {
	
	private String message;
	private String status;
	private String transactionId;
	
	public ApiResultError() {
		
	}
	
	public ApiResultError(String message, String status, String transactionId) {
		this.message = message;
		this.status = status;
		this.transactionId = transactionId;
	}
	
	public static ApiResultError createResponse(String message, String status, String transactionId) {
		return new ApiResultError(message, status, transactionId);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}
}