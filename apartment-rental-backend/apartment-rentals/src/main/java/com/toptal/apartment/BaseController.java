package com.toptal.apartment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.toptal.utils.Constants;

public class BaseController {

	private final Logger logger = LoggerFactory.getLogger(BaseController.class);

	public <T> ResponseEntity<T> buildSuccessResponse(T body) {
		logger.info("Request with URL '{}' processed successfully",
				((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
						.getRequestURI());
		return new ResponseEntity<T>(body, HttpStatus.OK);
	}

	public ResponseEntity<String> buildErrorResponse(String errorMessage, HttpStatus ststus) {
		logger.error(errorMessage);
		return new ResponseEntity<String>(errorMessage, ststus);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleAuthenticationException(Exception e) {
		logger.error("Exception Raised", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.INTERNAL_SERVER_ERROR);
	}

}
