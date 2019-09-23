package com.toptal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toptal.apartment.BaseController;
import com.toptal.models.JwtUserDetails;
import com.toptal.services.UserDetailService;
import com.toptal.utils.Constants;
import com.toptal.utils.UserDetailConverterUtil;

@RestController
@RequestMapping(value = "/api/user/**")
public class UserDetailsController extends BaseController {

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private UserDetailConverterUtil utils;

	@ResponseBody
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<?> registerNewUser(@RequestBody JwtUserDetails user) {
		// Check if all the details have been provided
		String errorMessage = utils.validateUserRegistrationDetails(user);
		if (!errorMessage.equals(Constants.BLANK_STRING))
			return buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);

		// Check if user already exists
		UserDetails details = userDetailService.loadUserByUsername(user.getUsername());
		if (null != details) {
			return buildErrorResponse(Constants.USER_ALREADY_EXISTS, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return buildSuccessResponse(this.userDetailService.registerNewUser(user));
	}

	@ResponseBody
	@RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<JwtUserDetails>> retrieveAllUsers() {
		return buildSuccessResponse(this.userDetailService.retrieveAllUsers());
	}

	@ResponseBody
	@Secured(Constants.ROLE_ADMIN)
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<?> updateUserDetails(@RequestBody JwtUserDetails user) {
		JwtUserDetails details = (JwtUserDetails) userDetailService.loadUserByUsername(user.getUsername());
		if (null == details) {
			return buildErrorResponse(Constants.USER_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String errorMessage = utils.validateUpdates(user);
		if (!errorMessage.equals(Constants.BLANK_STRING))
			return buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
	
		return buildSuccessResponse(this.userDetailService.updateUserDetails(user, details));
	}
	
	@ResponseBody
	@RequestMapping(value="/userDetails/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveUserDetails(@PathVariable("username") String username){
		return buildSuccessResponse(this.userDetailService.loadUserByUsername(username));
	}

}
