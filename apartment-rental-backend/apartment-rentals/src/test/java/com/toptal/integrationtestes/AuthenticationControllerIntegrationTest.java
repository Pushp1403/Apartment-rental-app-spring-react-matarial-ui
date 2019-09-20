package com.toptal.integrationtestes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.toptal.apartment.ApartmentRentalsApplication;
import com.toptal.models.JwtTokenRequest;
import com.toptal.models.JwtUserDetails;
import com.toptal.testutils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = ApartmentRentalsApplication.class)
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {

	Logger logger = LoggerFactory.getLogger(AuthenticationControllerIntegrationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestUtils utils;

	@Test
	public void testRegisterNewUser() throws Exception {
		JwtUserDetails user = utils.createUser();
		user.setUsername("testUser@testdomain.com");
		try {
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.username").exists());
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	@Test
	public void testUserAuthentication() throws Exception {
		JwtTokenRequest req = utils.createAuthenticationUser();
		JwtUserDetails user = utils.createUser();
		try {
			// Register user
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

			// Try authentication and check if valid token returned
			mockMvc.perform(post("/authenticate").content(utils.asJsonString(req))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	@Test
	public void testBadCredentials() throws Exception {
		JwtTokenRequest req = utils.createAuthenticationUser();
		JwtUserDetails user = utils.createUser();
		try {
			// register user
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

			// set a different password
			req.setPassword("InvalidPassword");

			// try to authenticate with invalid password
			mockMvc.perform(post("/authenticate").content(utils.asJsonString(req))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is(401));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	@Test
	public void testRegisterDuplicateUser() throws Exception {
		JwtUserDetails user = utils.createUser();
		try {
			// register use
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

			// try registering the same user again
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is(500));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	public void testMissingUserCredentials() throws Exception {
		JwtUserDetails user = utils.createUser();
		user.setUsername("");
		try {
			// Try registering user with blank username
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is(500));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	public void testRoleClientCanNotUpdateUserDetails() throws Exception {
		JwtUserDetails user = utils.createUser();
		user.getAuthorities().get(0).setRole("ROLE_CLIENT");
		try {
			// register user with ROLE_CLIENT
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

			// User should not be able to update the user
			user.setFirstName("New First Name");
			mockMvc.perform(post("/api/user/updateUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is(403));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}
}
