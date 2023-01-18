package com.spring.lts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lts.service.TokenService;

//import com.spring.lts.config.Token;

@RestController
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }
    
    @GetMapping
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
	
//    @GetMapping("/token")
//    public Token getToken(JwtAuthenticationToken jwtToken) {
//        return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
//    }
//
//    @GetMapping("/read")
//    public String read() {
//        return "Welcome to the internet, i'll be your guide";
//    }
//
//    @GetMapping("/write")
//    public String write() {
//        return "I know kung fu!";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "You can't judge me, i am justice itself";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "All your base are belong to us";
//    }
}