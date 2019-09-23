package com.toptal.models;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 4402868197766009610L;

	private String role;
	private String username;
	private Long id;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
