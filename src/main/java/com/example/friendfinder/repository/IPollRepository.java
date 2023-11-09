package com.example.friendfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.Polls;

@Repository
public interface IPollRepository extends JpaRepository<Polls, Long> {
	
	
}
