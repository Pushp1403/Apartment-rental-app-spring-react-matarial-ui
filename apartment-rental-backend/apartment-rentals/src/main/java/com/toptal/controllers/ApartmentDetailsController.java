package com.toptal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toptal.apartment.BaseController;
import com.toptal.models.ApartmentDetails;
import com.toptal.services.ApartmentDetailServiceImpl;
import com.toptal.utils.ApartmentDetailsConverterUtil;
import com.toptal.utils.ApartmentFilterCriteria;
import com.toptal.utils.Constants;

@RestController
@RequestMapping(value = "/api/apartment/**")
public class ApartmentDetailsController extends BaseController {
	
	@Autowired
	private ApartmentDetailServiceImpl apartmentDetailService;

	@Autowired
	private ApartmentDetailsConverterUtil utils;

	@ResponseBody
	@RequestMapping(value = "/saveApartment", method = RequestMethod.POST)
	@Secured({ Constants.ROLE_REALTOR, Constants.ROLE_ADMIN })
	public ResponseEntity<?> saveNewApartment(@RequestBody ApartmentDetails apartment) {
		String errorMessage = utils.validateApartmentDetails(apartment);
		if (!errorMessage.equals(Constants.BLANK_STRING))
			return buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
		ApartmentDetails details = apartmentDetailService.saveApartment(apartment);
		return buildSuccessResponse(details);
	}

	@ResponseBody
	@RequestMapping(value = "/listAllApartments", method = RequestMethod.GET)
	public ResponseEntity<List<ApartmentDetails>> listAllApartments() {
		return buildSuccessResponse(apartmentDetailService.listAllApartments());
	}

	@ResponseBody
	@RequestMapping(value = "/filterApartments", method = RequestMethod.POST)
	public ResponseEntity<List<ApartmentDetails>> filterApartments(@RequestBody ApartmentFilterCriteria criteria) {
		return buildSuccessResponse(apartmentDetailService.filterApartments(criteria));
	}

}
