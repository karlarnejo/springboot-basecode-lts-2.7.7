package com.spring.lts.model;

import com.spring.lts.entity.Userr;

public class LoginRequest {

	private String username;
	private String password;
	
	public LoginRequest() {
		
	}
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginRequest(Userr userEntity) {
		this.setUsername(userEntity.getUsername());
		this.setPassword(userEntity.getPassword());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
