package com.spring.lts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="roletoprivilege")
@NamedQuery(name="RoleToPrivilege.findAll", query="SELECT u FROM RoleToPrivilege u")
public class RoleToPrivilege {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleToPrivilegeId;
	
	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role rolePriv;
	
    @ManyToOne
    @JoinColumn(name = "privilegeId", referencedColumnName = "privilegeId")
    private Privilege privilege;
    
    public RoleToPrivilege() {
    	
    }

	public int getRoleToPrivilegeId() {
		return roleToPrivilegeId;
	}

	public void setRoleToPrivilegeId(int roleToPrivilegeId) {
		this.roleToPrivilegeId = roleToPrivilegeId;
	}

	public Role getRolePriv() {
		return rolePriv;
	}

	public void setRolePriv(Role rolePriv) {
		this.rolePriv = rolePriv;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
}