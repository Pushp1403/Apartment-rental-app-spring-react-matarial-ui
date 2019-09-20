package com.toptal.utils;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1080477837974374074L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
