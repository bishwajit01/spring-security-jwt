package com.vikram.bishwajit.springsecurityjdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author Bishwajit.
 * 
 *         DOCUMENTS for USER_SCHEMA
 *         https://docs.spring.io/spring-security/site/docs/4.0.4.RELEASE/reference/html/appendix-schema.html
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * In Memory Setting the in memory authentication with default Schema.
		 */
		/*
		 * auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
		 * .withUser(User.withUsername("user").password("pass").roles("USER"))
		 * .withUser(User.withUsername("admin").password("pass").roles("ADMIN"));
		 */
		/**
		 * In Memory Setting the in memory authentication with H2 DataBase.
		 */
		//auth.jdbcAuthentication().dataSource(dataSource);
		
		/**
		 * In Memory Setting the in memory authentication with H2 DataBase.
		 * Getting User Name by Queries.
		 */
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("Select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery("Select username, authority from authorities where username=?");
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
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
