package com.spring.lts.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT u FROM Role u")
public class Role {
	
	@Id
	private int roleId;
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private List<UserToRole> userToRole;
	
	@OneToMany(mappedBy = "rolePriv")
	private List<RoleToPrivilege> roleToPrivileges;
	
	public Role() {
		
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserToRole> getUserToRole() {
		return userToRole;
	}

	public void setUserToRole(List<UserToRole> userToRole) {
		this.userToRole = userToRole;
	}

	public List<RoleToPrivilege> getRoleToPrivileges() {
		return roleToPrivileges;
	}

	public void setRoleToPrivileges(List<RoleToPrivilege> roleToPrivileges) {
		this.roleToPrivileges = roleToPrivileges;
	}
}