package com.vikram.bishwajit.springsecurityjwt.service;

//import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vikram.bishwajit.springsecurityjwt.Model.MyUserDetails;
import com.vikram.bishwajit.springsecurityjwt.Model.User;
import com.vikram.bishwajit.springsecurityjwt.dao.UserRepository;

/**
 * @author Bishwajit.
 *
 */
@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/**
		 * When you don't want to connect to DATABASE.
		 */
		//return new org.springframework.security.core.userdetails.User("foo", "foo", new ArrayList<>());
		
		/**
		 * When you want to connect to DATABASE.
		 */
		Optional<User> user = userRepository.findByUserName(username);
		// Throwing Exception when user is not found in DATABASE
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found!!" + username));
		return user.map(MyUserDetails::new).get();
	}

}
