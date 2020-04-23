package com.vikram.bishwajit.springsecurityldap.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Bishwajit.
 *
 */
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	/**
	 * LDAP User Setup --> https://spring.io/guides/gs/authenticating-ldap/
	 * Dn = Distinguish Name.
	 * Ou = Organization Unit.
	 * Uid = User ID.
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
								.userDnPatterns("uid={0},ou=people")  
								.groupSearchBase("ou=groups")
								.contextSource()
								.url("ldap://localhost:9091/dc=springframework,dc=org")
								.and()
								.passwordCompare()
								.passwordEncoder(new BCryptPasswordEncoder())
								.passwordAttribute("userPassword");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();

	}

	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
