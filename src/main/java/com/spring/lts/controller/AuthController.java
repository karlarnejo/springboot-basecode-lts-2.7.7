package com.spring.lts.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lts.service.AuthenticationService;
import com.spring.lts.service.UserDetailsCustomService;
import com.spring.lts.common.ApiResultAuth;
import com.spring.lts.common.ApiResultRest;
import com.spring.lts.common.UserDetailsModel;
import com.spring.lts.model.AuthenticationModel;

@RestController
public class AuthController {
	
	private final UserDetailsCustomService userDetailsCustomService;
	private final AuthenticationService authenticationService;
	  
	public AuthController(UserDetailsCustomService userDetailsCustomService, AuthenticationService authenticationService) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/authenticate")
    public ApiResultAuth createAuthenticationToken(@RequestBody AuthenticationModel authenticationRequest) throws Exception {
    	authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    	
    	final UserDetails userDetails = userDetailsCustomService
				.loadUserByUsername(authenticationRequest.getUsername());
        
        final String token = authenticationService.generateToken(userDetails);
        
        UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setUsername(userDetails.getUsername());
        
        return ApiResultAuth.createResponse(userDetailsModel, "CUSTOM_SUCCESS_STATUS", "Message to be added later", token);
    }

    @PostMapping("/")
	public ApiResultRest helloWorld(@RequestBody AuthenticationModel authenticationRequest) {

    	return ApiResultRest.createResponse("String", "CUSTOM_SUCCESS_STATUS", "Message to be added later");
    }
}