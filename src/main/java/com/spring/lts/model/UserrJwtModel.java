package com.spring.lts.model;

import java.util.List;

import com.spring.lts.entity.UserToRole;
import com.spring.lts.entity.Userr;

public class UserrJwtModel {
	
	private String username;
	private String password;
	private boolean isEnabled;
	private boolean isNotLocked;
	private boolean isAccountNotExpired;
	private boolean isPasswordNotExpired;
	private List<UserToRole> userToRole;
	
	public UserrJwtModel() {
		
	}
	
	public UserrJwtModel(Userr userr) {
		this.setUsername(userr.getUsername());
		this.setPassword(userr.getPassword());
		this.setEnabled(userr.isEnabled());
		this.setNotLocked(userr.isNotLocked());
		this.setAccountNotExpired(userr.isAccountNotExpired());
		this.setPasswordNotExpired(userr.isPasswordNotExpired());
		this.setUserToRole(userr.getUserToRole());
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<UserToRole> getUserToRole() {
		return userToRole;
	}

	public void setUserToRole(List<UserToRole> userToRole) {
		this.userToRole = userToRole;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public boolean isAccountNotExpired() {
		return isAccountNotExpired;
	}

	public void setAccountNotExpired(boolean isAccountNotExpired) {
		this.isAccountNotExpired = isAccountNotExpired;
	}

	public boolean isPasswordNotExpired() {
		return isPasswordNotExpired;
	}

	public void setPasswordNotExpired(boolean isPasswordNotExpired) {
		this.isPasswordNotExpired = isPasswordNotExpired;
	}
}