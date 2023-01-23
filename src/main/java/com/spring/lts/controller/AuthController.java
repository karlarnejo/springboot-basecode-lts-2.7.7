package com.spring.lts.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lts.model.UserrJwtModel;
import com.spring.lts.service.TokenService;
import com.spring.lts.common.ApiResultAuth;
import com.spring.lts.common.ApiResultRest;
import com.spring.lts.common.UserDetailsModel;
import com.spring.lts.dao.CrudRepositoryUserr;
import com.spring.lts.model.AuthenticationModel;

@RestController
public class AuthController {
	
	private final TokenService tokenService;
	private final AuthenticationManager authenticationManager;
	private final CrudRepositoryUserr crudRepositoryUserr;
    
    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, CrudRepositoryUserr crudRepositoryUserr) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.crudRepositoryUserr = crudRepositoryUserr;
    }
    
    @PostMapping("/authenticate")
    public ApiResultAuth createAuthenticationToken(@RequestBody AuthenticationModel authenticationRequest) {
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
    	UserrJwtModel response = new UserrJwtModel(crudRepositoryUserr.findByUsername(authenticationRequest.getUsername()));
		return ApiResultRest.createResponse(response, "CUSTOM_SUCCESS_STATUS", "Message to be added later");
	}
}