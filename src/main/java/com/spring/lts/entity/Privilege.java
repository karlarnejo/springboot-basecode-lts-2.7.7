package com.spring.lts.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="privilege")
@NamedQuery(name="Privilege.findAll", query="SELECT u FROM Privilege u")
public class Privilege {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int privilegeId;
	private String privilegeName;
	
	@OneToMany(mappedBy = "privilege")
	private List<RoleToPrivilege> roleToPrivileges;
	
	public Privilege() {
		
	}

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public List<RoleToPrivilege> getRoleToPrivileges() {
		return roleToPrivileges;
	}

	public void setRoleToPrivileges(List<RoleToPrivilege> roleToPrivileges) {
		this.roleToPrivileges = roleToPrivileges;
	}
}