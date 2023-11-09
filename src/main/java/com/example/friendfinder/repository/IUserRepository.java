package com.example.friendfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	// method to get friends of a user by name
	List<User> findByFirstName(String firstName);
	
	// method to get friendList of a user
	@Query(value="select friends from users where user_id=:user_id", nativeQuery=true)
	List<User> getAllFriendsByUserId(@Param("user_id") long userId);
}
