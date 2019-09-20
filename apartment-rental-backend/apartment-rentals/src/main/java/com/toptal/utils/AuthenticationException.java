package com.toptal.utils;

public class AuthenticationException extends RuntimeException {
	
	private static final long serialVersionUID = 7278486413894542207L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
