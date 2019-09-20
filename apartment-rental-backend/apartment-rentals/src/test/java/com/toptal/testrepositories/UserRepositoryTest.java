package com.toptal.testrepositories;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.toptal.apartment.ApartmentRentalsApplication;
import com.toptal.entities.Authority;
import com.toptal.entities.User;
import com.toptal.entities.UserDetail;
import com.toptal.repositories.IUserDetailsRepository;
import com.toptal.repositories.IUserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApartmentRentalsApplication.class)
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IUserDetailsRepository userDetailsRepository;

	@Autowired
	private IUserRepository userRepository;

	@Test
	public void whenSaved_thenCheckSaved() {
		// given
		User user = createTestUser();
		entityManager.persist(user);
		entityManager.flush();

		// when
		List<User> users = userRepository.findAll();

		// then
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).getRoles().size(), 1);
		assertEquals(users.get(0).getUserDetails().getFirstName(), "pushpendra");
	}

	@Test
	public void whenSavedUserDetails_thenSaved() {
		// given
		UserDetail details = createUserDetails();
		entityManager.persist(details);
		entityManager.flush();

		// when
		List<UserDetail> users = userDetailsRepository.findAll();

		// then
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).getFirstName(), "pushpendra");
	}

	private User createTestUser() {
		User user = new User();
		user.setUsername("userName");
		user.setPassword("youcantguessit");
		UserDetail details = createUserDetails();
		user.setUserDetails(details);
		List<Authority> authList = createAuthList();
		user.setRoles(authList);
		return user;

	}

	private List<Authority> createAuthList() {
		Authority auth = new Authority();
		auth.setAuthority("ROLE_TEST");
		List<Authority> authList = new ArrayList<Authority>();
		authList.add(auth);
		return authList;
	}

	private UserDetail createUserDetails() {
		UserDetail details = new UserDetail();
		details.setFirstName("pushpendra");
		details.setLastName("sharma");
		details.setUsername("userName");
		return details;
	}

}
