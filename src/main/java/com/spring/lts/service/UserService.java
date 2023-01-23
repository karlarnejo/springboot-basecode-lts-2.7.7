package com.spring.lts.service;

import com.spring.lts.model.LoginRequest;

public interface UserService {
	public LoginRequest findByUsername(String username);
}