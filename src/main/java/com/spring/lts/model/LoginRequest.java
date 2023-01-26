package com.spring.lts.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.lts.entity.Userr;

@Service
public class LoginRequest implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean isEnabled;
	private boolean isNotLocked;
	private boolean isAccountNotExpired;
	private boolean isPasswordNotExpired;
	private Collection<? extends GrantedAuthority> authorities;

	public LoginRequest() {

	}
	
	public LoginRequest(Userr userEntity) {
		this.setUsername(userEntity.getUsername());
		this.setPassword(userEntity.getPassword());
	}

	public LoginRequest(String username, String password, boolean isEnabled, boolean isNotLocked,
			boolean isAccountNotExpired, boolean isPasswordNotExpired, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isNotLocked = isNotLocked;
		this.isAccountNotExpired = isAccountNotExpired;
		this.isPasswordNotExpired = isPasswordNotExpired;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNotExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isNotLocked;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isPasswordNotExpired;
	}
}