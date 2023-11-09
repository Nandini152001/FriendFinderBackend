package com.example.friendfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.User;

@Repository
public interface IAdminRepository extends JpaRepository<User,Long> {

}
