package com.spring.lts.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lts.service.TokenService;
import com.spring.lts.common.ApiResultAuth;
import com.spring.lts.common.ApiResultRest;
import com.spring.lts.common.UserDetailsModel;
import com.spring.lts.model.AuthenticationModel;

@RestController
public class AuthController {
	
	private final TokenService tokenService;
	private final AuthenticationManager authenticationManager;
    
    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
    
    @PostMapping("/authenticate")
    public ApiResultAuth createAuthenticationToken(@RequestBody AuthenticationModel authenticationRequest) throws Exception {
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        
    	final UserDetails userDetails = tokenService
				.loadUserByUsername(authenticationRequest.getUsername());
        
        final String token = tokenService.generateToken(userDetails);
        
        UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setUsername(userDetails.getUsername());
        
        return ApiResultAuth.createResponse(userDetailsModel, "CUSTOM_SUCCESS_STATUS", "Message to be added later", token);
    }

    @PostMapping("/")
	public ApiResultRest helloWorld(@RequestBody AuthenticationModel authenticationRequest) {

    	return ApiResultRest.createResponse("String", "CUSTOM_SUCCESS_STATUS", "Message to be added later");
    }
}