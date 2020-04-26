package com.vikram.bishwajit.springsecurityjwt.Model;

/**
 * @author Bishwajit.
 *
 */
public class AuthenticationResponse {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

}
