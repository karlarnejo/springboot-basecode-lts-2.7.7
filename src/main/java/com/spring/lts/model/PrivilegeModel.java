package com.spring.lts.model;

import com.spring.lts.entity.Privilege;

public class PrivilegeModel {

	private String privilegeName;
	
	public PrivilegeModel() {
		
	}
	
	public PrivilegeModel(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	
	public PrivilegeModel(Privilege privilege) {
		this.privilegeName = privilege.getPrivilegeName();
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
}
