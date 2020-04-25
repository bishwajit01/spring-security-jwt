package com.vikram.bishwajit.springsecurityjwt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bishwajit.
 *
 */
@RestController
@RequestMapping("/")
public class SpringSecurityController {

	@GetMapping({ "home" })
	public String home() {
		return "<h1>Welcome, Home !!</h1>";
	}
}
