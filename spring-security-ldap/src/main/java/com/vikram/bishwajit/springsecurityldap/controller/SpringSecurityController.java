package com.vikram.bishwajit.springsecurityldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishwajit.
 *
 */
@RestController
@RequestMapping
public class SpringSecurityController {

	@GetMapping("home")
	public String home() {
		return "<h1>WELCOME !!<h1>";
	}
}
