package com.vikram.bishwajit.springsecurityjpa.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.bishwajit.springsecurityjpa.Model.User;

/**
 * @author Bishwajit.
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	Optional<User> findByUserName(String username);
}
