package com.example.friendfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.Posts;

@Repository
public interface IPostRepository extends JpaRepository<Posts, Long> {
	
}
