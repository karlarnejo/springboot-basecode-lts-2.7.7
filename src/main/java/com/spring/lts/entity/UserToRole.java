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
@Table(name="usertorole")
@NamedQuery(name="UserToRole.findAll", query="SELECT u FROM UserToRole u")
public class UserToRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userToRoleId;
	
    @ManyToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
    private Userr userr;
    
    @ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;
    
	public UserToRole() {
		
	}

	public int getUserToRoleId() {
		return userToRoleId;
	}

	public void setUserToRoleId(int userToRoleId) {
		this.userToRoleId = userToRoleId;
	}

	public Userr getUserr() {
		return userr;
	}

	public void setUserr(Userr userr) {
		this.userr = userr;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}