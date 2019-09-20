package com.toptal.models;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {
	private static final long serialVersionUID = -6977115103101052147L;

	private final String token;

	public JwtTokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
