package com.spring.lts.model;

import com.spring.lts.entity.Role;

public class RoleModel {

	private String roleName;
	
	public RoleModel() {
		
	}
	
	public RoleModel(String roleName) {
		this.roleName = roleName;
	}
	
	public RoleModel(Role role) {
		this.roleName = role.getRoleName();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
