package com.spring.lts.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.lts.exception.UserAccountDisabledException;
import com.spring.lts.exception.UserAccountExpiredException;
import com.spring.lts.exception.UserAccountLockedException;
import com.spring.lts.exception.UserInvalidPasswordException;
import com.spring.lts.exception.UserInvalidUsernameException;
import com.spring.lts.exception.UserPasswordExpiredException;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthenticationServiceImpl implements AuthenticationService {

	private final JwtEncoder encoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationServiceImpl(JwtEncoder encoder, AuthenticationManager authenticationManager) {
		this.encoder = encoder;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public String generateToken(UserDetails authentication) {
		// TODO Auto-generated method stub
		
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
	public void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UserAccountDisabledException(e, username);
		} catch (LockedException e) {
			throw new UserAccountLockedException(e, username);
		} catch (AccountExpiredException e) {
			throw new UserAccountExpiredException(e, username);
		} catch (CredentialsExpiredException e) {
			throw new UserPasswordExpiredException(e, username);
		} catch (InternalAuthenticationServiceException e) {
			throw new UserInvalidUsernameException(e, username);
		} catch (BadCredentialsException e) {
			throw new UserInvalidPasswordException(e, username);
		}
	}
}
