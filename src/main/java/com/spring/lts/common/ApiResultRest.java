package com.spring.lts.common;

public class ApiResultRest {

	private Object data;
	private String message;
	private String status;
	
	public ApiResultRest() {
		
	}
	
	public ApiResultRest(Object data, String status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
	}
	
	public static ApiResultRest createResponse(Object data, String status, String message) {
		return new ApiResultRest(data, status, message);
	}
	
	public static ApiResultRest createResponse(Void data, String status, String message) {
		return new ApiResultRest(data, status, message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}