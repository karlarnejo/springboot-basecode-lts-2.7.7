package com.spring.lts.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.lts.dao.CrudRepositoryUserr;
import com.spring.lts.entity.RoleToPrivilege;
import com.spring.lts.entity.UserToRole;
import com.spring.lts.model.LoginRequest;
import com.spring.lts.model.UserrJwtModel;

@Service
public class UserDetailsCustomService implements UserDetailsService {

	private final CrudRepositoryUserr crudRepositoryUserr;
	
    public UserDetailsCustomService(CrudRepositoryUserr crudRepositoryUserr) {
        this.crudRepositoryUserr = crudRepositoryUserr;
    }
    
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsCustomService.class);
    
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
