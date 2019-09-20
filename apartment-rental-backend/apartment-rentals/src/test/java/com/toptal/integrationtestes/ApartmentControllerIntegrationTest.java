package com.toptal.integrationtestes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
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
import org.springframework.test.web.servlet.MvcResult;
import com.toptal.apartment.ApartmentRentalsApplication;
import com.toptal.models.JwtTokenRequest;
import com.toptal.models.JwtUserDetails;
import com.toptal.testutils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = ApartmentRentalsApplication.class)
@AutoConfigureMockMvc
public class ApartmentControllerIntegrationTest {

	Logger logger = LoggerFactory.getLogger(AuthenticationControllerIntegrationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestUtils utils;

	@Before
	public void setUp() throws Exception {
		JwtUserDetails user = utils.createUser();
		try {
			mockMvc.perform(post("/api/user/registerUser").content(utils.asJsonString(user))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	@Test
	public void testNoOneCanListApartmentsWithoutAuthentication() throws Exception {
		try {
			mockMvc.perform(get("/api/apartment/listAllApartments").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(401));
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

	@Test
	public void testAuthenticatedUserCanListApartments() throws Exception {
		JwtTokenRequest req = utils.createAuthenticationUser();
		try {
			MvcResult result = mockMvc.perform(post("/authenticate").content(utils.asJsonString(req))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
			String token = result.getResponse().getContentAsString();
			JSONObject object = new JSONObject(token);
			mockMvc.perform(
					get("/api/apartment/listAllApartments").header("Authorization", "Bearer " + object.get("token"))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			logger.debug("failed", e);
			throw new Exception(e);
		}
	}

}
