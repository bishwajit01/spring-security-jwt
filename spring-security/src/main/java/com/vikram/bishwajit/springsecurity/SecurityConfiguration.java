package com.vikram.bishwajit.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Bishwajit.
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * Setting the in memory authentication.
		 */
		auth.inMemoryAuthentication()
						.withUser("vikram").password("testing1").roles("USER")
						.and()
						.withUser("bishu").password("testing1").roles("ADMIN");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
								.antMatchers("/admin").hasRole("ADMIN")
								.antMatchers("/user").hasAnyRole("ADMIN", "USER")
								.antMatchers("/").permitAll()
								.and().formLogin();
	}

	@Bean
	public PasswordEncoder getPassword() {
		return NoOpPasswordEncoder.getInstance();
	}

}
