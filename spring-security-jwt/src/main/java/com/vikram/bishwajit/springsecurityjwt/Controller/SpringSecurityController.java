package com.vikram.bishwajit.springsecurityjwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.bishwajit.springsecurityjwt.Model.AuthenticationRequest;
import com.vikram.bishwajit.springsecurityjwt.Model.AuthenticationResponse;
import com.vikram.bishwajit.springsecurityjwt.service.MyUserDetailService;
import com.vikram.bishwajit.springsecurityjwt.util.SpringSecurityJWTUtil;

/**
 * @author Bishwajit.
 *
 */
@RestController
@RequestMapping("/")
public class SpringSecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myUserDetailsService;

	@Autowired
	private SpringSecurityJWTUtil springSecurityJWTUtil;

	@GetMapping("home")
	public String home() {
		return "<h1>Welcome, Home !!</h1>";
	}

	@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationRequest(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			System.err.println("*********Invalid username password*********");
			throw new Exception("Invalid username and password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwtGenerated = springSecurityJWTUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwtGenerated));
	}
}
