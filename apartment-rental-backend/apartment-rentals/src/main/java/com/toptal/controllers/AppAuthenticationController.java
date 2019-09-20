package com.toptal.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toptal.apartment.BaseController;
import com.toptal.models.JwtTokenRequest;
import com.toptal.models.JwtTokenResponse;
import com.toptal.security.JwtTokenUtil;
import com.toptal.services.UserDetailService;
import com.toptal.utils.AuthenticationException;
import com.toptal.utils.Constants;
import com.toptal.utils.UserDetailConverterUtil;

@Component
@RestController
public class AppAuthenticationController extends BaseController {

	@Value("{${jwt.http.request.header}}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private UserDetailConverterUtil utils;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {
		String errorMessage = utils.validateAuthenticationDetails(authenticationRequest);
		if (!errorMessage.equals(Constants.BLANK_STRING))
			return buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = tokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtTokenResponse(token));
	}

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);

		if (tokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = tokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException(Constants.USER_DISABLED, e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException(Constants.INVALID_CREDENTIALS, e);
		}
	}
}
