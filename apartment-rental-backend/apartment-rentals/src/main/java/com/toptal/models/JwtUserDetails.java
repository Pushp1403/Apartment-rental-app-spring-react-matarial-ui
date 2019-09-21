package com.toptal.models;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = -7482314997573752450L;

	private String username;
	private String firstName;
	private String lastName;
	private String secretKey;
	private List<Authority> authorities;

	public JwtUserDetails() {
		super();
	}

	public JwtUserDetails(String username, @JsonProperty("secretKey") String secretKey, String firstName,
			String lastName, List<Authority> authorities) {
		this.username = username;
		this.secretKey = secretKey;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;
	}

	@JsonIgnore
	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return secretKey;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

}
