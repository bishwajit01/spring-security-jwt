package com.vikram.bishwajit.springsecurityjwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikram.bishwajit.springsecurityjwt.Model.User;

/**
 * 
 * @author Bishwajit.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String username);

}
