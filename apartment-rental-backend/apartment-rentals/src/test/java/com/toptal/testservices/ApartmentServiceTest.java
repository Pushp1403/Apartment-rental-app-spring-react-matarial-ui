package com.toptal.testservices;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toptal.entities.Apartment;
import com.toptal.models.ApartmentDetails;
import com.toptal.repositories.IApartmentRepository;
import com.toptal.services.ApartmentDetailServiceImpl;
import com.toptal.services.IApartmentDetailServices;
import com.toptal.testutils.TestUtils;
import com.toptal.utils.ApartmentDetailsConverterUtil;
import com.toptal.utils.ApartmentFilterCriteria;

@RunWith(SpringRunner.class)
public class ApartmentServiceTest {

	@TestConfiguration
	static class ApartmentServiceImplTestConfigurer {
		@Bean
		public IApartmentDetailServices apartmentDetailsService() {
			return new ApartmentDetailServiceImpl();
		}

		@Bean
		public ApartmentDetailsConverterUtil util() {
			return new ApartmentDetailsConverterUtil();
		}
		
		@Bean
		public TestUtils utilities() {
			return new TestUtils();
		}
	}

	@Autowired
	private IApartmentDetailServices apartmentService;

	@MockBean
	private IApartmentRepository repository;

	@MockBean
	private ApartmentDetailsConverterUtil converter;

	@Autowired
	private TestUtils utils;

	@Before
	public void setUp() {
		List<Apartment> apartments = utils.createTestApartments();
		Mockito.when(repository.findByEnabled(true)).thenReturn(apartments);
		Mockito.when(converter.convertApartmentEntityToApartmentDetailsModel(apartments.get(0)))
				.thenReturn(utils.createApartment());
	}

	@Test
	public void whenSaved_thenCheckSavedSuccessfully() {

		// When
		List<ApartmentDetails> apts = apartmentService.listAllApartments();

		// Than
		assertEquals(apts.size(), 1);
		assertEquals(apts.get(0).getAddress(), "test address");
	}

	@Test
	public void testFilterApartmentWithMinPrice() {
		// Arrange
		ApartmentFilterCriteria criteria = new ApartmentFilterCriteria();
		criteria.setMinPrice(23.23);
		Mockito.when(repository.findAll(converter.createSpecification(criteria)))
				.thenReturn(utils.createTestApartments());

		// Act
		List<ApartmentDetails> details = apartmentService.filterApartments(criteria);

		// Assert
		assertEquals(details.size(), 1);
		assertEquals(details.get(0).getFloorArea(), 23.34, 0);
	}

}
