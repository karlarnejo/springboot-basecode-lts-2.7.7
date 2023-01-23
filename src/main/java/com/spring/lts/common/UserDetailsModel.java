package com.spring.lts.common;

public class UserDetailsModel {
	
	private String username;
	
	public UserDetailsModel() {
		
	}
	
	public UserDetailsModel(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}