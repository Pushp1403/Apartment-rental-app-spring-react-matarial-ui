package com.toptal.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.toptal.entities.Apartment;
import com.toptal.models.ApartmentDetails;
import com.toptal.models.JwtUserDetails;
import com.toptal.repositories.IApartmentRepository;
import com.toptal.utils.ApartmentDetailsConverterUtil;
import com.toptal.utils.ApartmentFilterCriteria;

@Service
@Primary
public class ApartmentDetailServiceImpl implements IApartmentDetailServices {

	@Autowired
	private IApartmentRepository apartmentRepository;

	@Autowired
	private ApartmentDetailsConverterUtil converter;

	@Override
	public ApartmentDetails saveApartment(ApartmentDetails apartment) {
		Apartment details = converter.convertApartmentModelToApartmentEntity(apartment);
		details = apartmentRepository.save(details);
		return converter.convertApartmentEntityToApartmentDetailsModel(details);
	}

	public List<ApartmentDetails> listAllApartments() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String role = ((JwtUserDetails) principal).getAuthorities().get(0).getAuthority();
			if (role.equals("ROLE_ADMIN") || role.equals("ROLE_REALTOR")) {
				return getApartmentDetails(apartmentRepository.findByEnabled(true));
			}
		}
		List<Apartment> apartments = apartmentRepository.findByEnabledAndState(true, true);
		return getApartmentDetails(apartments);

	}

	public List<ApartmentDetails> filterApartments(ApartmentFilterCriteria criteria) {
		Specification<Apartment> specification = converter.createSpecification(criteria);
		List<Apartment> apartments = apartmentRepository.findAll(specification);
		return getApartmentDetails(apartments);
	}

	private List<ApartmentDetails> getApartmentDetails(List<Apartment> apartments) {
		List<ApartmentDetails> returnList = apartments.stream().map(apt -> {
			return converter.convertApartmentEntityToApartmentDetailsModel(apt);
		}).collect(Collectors.toList());
		return returnList;
	}

}
