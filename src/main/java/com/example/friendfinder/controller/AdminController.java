package com.example.friendfinder.controller;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.friendfinder.service.IAdminService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	IAdminService adminServ;	// adding dependencies for admin service interface 
	
	private Logger logger = LogManager.getLogger();
	
	@PutMapping("/admin/unblockUser/{userId}")	// annotation to update the data in the database by providing userId
	ResponseEntity<String> unblockUserAccount(@PathVariable Long userId) {
		logger.info("User got unbanned");
		adminServ.unblockUserAccount(userId);	// calling service method to unblock a user
		String message="You are unbanned";
		return new ResponseEntity<>(message,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/blockUser/{userId}")	// annotation to update the data in the database by providing userId
	ResponseEntity<String> blockUserAccount(@PathVariable Long userId) {
		adminServ.blockUserAccount(userId);	// calling service method to block a user
		String message="You are banned";
		return new ResponseEntity<>(message,HttpStatus.CREATED);
	}
}
