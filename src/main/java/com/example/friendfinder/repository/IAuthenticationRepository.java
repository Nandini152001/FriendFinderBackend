package com.example.friendfinder.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.User;

@Repository
public interface IAuthenticationRepository extends JpaRepository<User, Long> {

	//custom methods , not provided by JPA repository
	Optional<User> findByEmail(String email);
}
