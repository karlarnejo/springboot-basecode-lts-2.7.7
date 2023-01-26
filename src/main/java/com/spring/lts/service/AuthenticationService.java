package com.spring.lts.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

	public String generateToken(UserDetails authentication);
	public void authenticate(String username, String password) throws Exception;
}
