package com.spring.lts.common;

public class ApiResultAuth {
	
	private Object data;
	private String message;
	private String status;
	private String token;

	public ApiResultAuth() {
		
	}
	
	public ApiResultAuth(Object data, String status, String message, String token) {
		this.data = data;
		this.status = status;
		this.message = message;
		this.token = token;
	}
	
	public static ApiResultAuth createResponse(Object data, String status, String message, String token) {
		return new ApiResultAuth(data, status, message, token);
	}

	public static ApiResultAuth createResponse(Void data, String status, String message, String token) {
		return new ApiResultAuth(data, status, message, token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
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
}