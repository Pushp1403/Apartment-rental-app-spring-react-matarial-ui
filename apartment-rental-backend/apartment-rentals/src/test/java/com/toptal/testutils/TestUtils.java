package com.toptal.testutils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toptal.entities.Apartment;
import com.toptal.models.ApartmentDetails;
import com.toptal.models.Authority;
import com.toptal.models.JwtTokenRequest;
import com.toptal.models.JwtUserDetails;

@Component
@Primary
public class TestUtils {

	public JwtTokenRequest createAuthenticationUser() {
		JwtTokenRequest req = new JwtTokenRequest();
		req.setUsername("pushp1403@gmail.com");
		req.setPassword("aradhana");
		return req;

	}

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public JwtUserDetails createUser() {
		JwtUserDetails user = new JwtUserDetails();
		user.setFirstName("pushpendra");
		user.setLastName("Sharma");
		user.setUsername("pushp1403@gmail.com");
		user.setSecretKey("aradhana");
		List<com.toptal.models.Authority> auth = createRoleForUser("ROLE_ADMIN");
		user.setAuthorities(auth);
		return user;
	}
	
	public JwtUserDetails createUser(String role) {
		JwtUserDetails user = new JwtUserDetails();
		user.setFirstName("pushpendra");
		user.setLastName("Sharma");
		user.setUsername("pushp1403@gmail.com");
		user.setSecretKey("aradhana");
		List<com.toptal.models.Authority> auth = createRoleForUser(role);
		user.setAuthorities(auth);
		return user;
	}

	private List<com.toptal.models.Authority> createRoleForUser(String role) {
		List<com.toptal.models.Authority> auth = new ArrayList<>();
		Authority authority = new Authority();
		authority.setRole(role);
		auth.add(authority);
		return auth;
	}

	public List<Apartment> createTestApartments() {
		List<Apartment> apts = new ArrayList<>();
		Apartment apartment = createTestEntity();
		apts.add(apartment);
		return apts;
	}

	public Apartment createTestEntity() {
		Apartment apartment = new Apartment();
		apartment.setAddress("test address");
		apartment.setDescription("this apartment is for testing purposes");
		apartment.setFloorArea(23.34);
		apartment.setName("My Test apartment");
		return apartment;
	}

	public ApartmentDetails createApartment() {
		ApartmentDetails apartment = new ApartmentDetails();
		apartment.setAddress("test address");
		apartment.setDescription("this apartment is for testing purposes");
		apartment.setFloorArea(23.34);
		apartment.setName("My Test apartment");
		return apartment;
	}
}
