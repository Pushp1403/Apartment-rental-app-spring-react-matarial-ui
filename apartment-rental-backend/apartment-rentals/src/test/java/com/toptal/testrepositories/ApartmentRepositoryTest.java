package com.toptal.testrepositories;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.toptal.entities.Apartment;
import com.toptal.repositories.IApartmentRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApartmentRentalsApplication.class)
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class ApartmentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IApartmentRepository apartmentRepository;

	@Test
	public void whenSaved_thenCheckSaved() {
		// given
		Apartment apartment = createTestApartment();
		entityManager.persist(apartment);
		entityManager.flush();

		// when
		List<Apartment> apts = apartmentRepository.findAll();

		// then
		assertThat(apts.size() == 1);
		assertThat(apts.get(0).getName().equals("My Test apartment"));
	}
	
	@Test
	public void whenUpdated_thenCheckUpdated() {
		//given
		Apartment apartment = createTestApartment();
		entityManager.persist(apartment);
		entityManager.flush();
		
		//when
		List<Apartment> apts = apartmentRepository.findAll();
		Apartment apt = apts.get(0);
		apt.setName("Updated Name");
		entityManager.persist(apt);
		entityManager.flush();
		
		//then
		List<Apartment> updatedApts = apartmentRepository.findAll();
		assertThat(updatedApts.size() == 1);
		assertThat(updatedApts.get(0).getName().equals("Updated Name"));

		
	}
	
	private Apartment createTestApartment() {
		Apartment apartment = new Apartment();
		apartment.setAddress("test address");
		apartment.setDescription("this apartment is for testing purposes");
		apartment.setFloorArea(2234);
		apartment.setName("My Test apartment");
		return apartment;
	}

}
