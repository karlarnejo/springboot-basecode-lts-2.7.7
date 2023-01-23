package com.spring.lts.service;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import com.spring.lts.dao.CrudRepositoryUserr;
import com.spring.lts.model.UserrJwtModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TokenService implements UserDetailsService {

	private final JwtEncoder encoder;
	private final CrudRepositoryUserr crudRepositoryUserr;

    public TokenService(JwtEncoder encoder, CrudRepositoryUserr crudRepositoryUserr) {
        this.encoder = encoder;
        this.crudRepositoryUserr = crudRepositoryUserr;
    }

    public String generateToken(UserDetails authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getUsername())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	UserrJwtModel response = new UserrJwtModel(crudRepositoryUserr.findByUsername(username));
    	
    	if (!response.getUsername().equals("")) {
			return new User(response.getUsername(), response.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
