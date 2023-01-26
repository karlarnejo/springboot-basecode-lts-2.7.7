package com.spring.lts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import com.spring.lts.dao.CrudRepositoryUserr;
import com.spring.lts.entity.RoleToPrivilege;
import com.spring.lts.entity.UserToRole;
import com.spring.lts.model.LoginRequest;
import com.spring.lts.model.UserrJwtModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TokenService implements UserDetailsService {

	private final JwtEncoder encoder;
	private final CrudRepositoryUserr crudRepositoryUserr;

    public TokenService(JwtEncoder encoder, CrudRepositoryUserr crudRepositoryUserr) {
        this.encoder = encoder;
        this.crudRepositoryUserr = crudRepositoryUserr;
    }
    
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

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
    		
    		logger.debug("There is a username found {}.", response.getUsername());
    		
    		Set<GrantedAuthority> authorities = new HashSet<>();

    		for (UserToRole userToRole : response.getUserToRole()) {
    			logger.debug("Roles found for " + response.getUsername() + " are: " + userToRole.getRole().getRoleName());
	            authorities.add(new SimpleGrantedAuthority(userToRole.getRole().getRoleName()));
	            for (RoleToPrivilege userRoleToRolePrivileges : userToRole.getRole().getRoleToPrivileges()) {
	            	logger.debug("Privileges found for " + response.getUsername() + " are: " + userRoleToRolePrivileges.getPrivilege().getPrivilegeName());
	            	authorities.add(new SimpleGrantedAuthority(userRoleToRolePrivileges.getPrivilege().getPrivilegeName()));
	            }
    		}

    		return new LoginRequest(response.getUsername(), response.getPassword(), response.isEnabled(), response.isNotLocked(), response.isAccountNotExpired(), response.isPasswordNotExpired(), authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
