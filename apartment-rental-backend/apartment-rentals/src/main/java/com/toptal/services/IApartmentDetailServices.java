package com.toptal.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.toptal.models.ApartmentDetails;
import com.toptal.utils.ApartmentFilterCriteria;

@Service
public interface IApartmentDetailServices {

	ApartmentDetails saveApartment(ApartmentDetails apartment);

	List<ApartmentDetails> listAllApartments();

	List<ApartmentDetails> filterApartments(ApartmentFilterCriteria criteria);

}
