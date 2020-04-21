package com.vikram.bishwajit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishwajit.
 *
 */
@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping("home")
	public String home() {
		return "<h1>WELCOME</h1>";
	}

	@GetMapping("user")
	public String user() {
		return "<h1>WELCOME!!  User!! </h1>";
	}

	@GetMapping("admin")
	public String admin() {
		return "<h1>WELCOME!!  Admin!! </h1>";
	}

}
