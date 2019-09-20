package com.toptal.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "user_auth")
public class User implements Serializable {

	private static final long serialVersionUID = 2744261772925381766L;
	@Id
	private String username;
	private String password;
	private boolean enabled;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = UserDetail.class)
	@JoinColumn(name = "username")
	private UserDetail userDetails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Authority.class)
	private List<Authority> roles;

	@Nullable
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, targetEntity = Apartment.class)
	private List<Apartment> apartmentByThisRealtor;

	public UserDetail getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetail userDetails) {
		this.userDetails = userDetails;
	}

	public List<Authority> getRoles() {
		return roles;
	}

	public void setRoles(List<Authority> roles) {
		this.roles = roles;
	}

	public List<Apartment> getApartmentByThisRealtor() {
		return apartmentByThisRealtor;
	}

	public void setApartmentByThisRealtor(List<Apartment> apartmentByThisRealtor) {
		this.apartmentByThisRealtor = apartmentByThisRealtor;
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
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
