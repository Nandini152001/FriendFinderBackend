package com.example.friendfinder.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserOutputDto {
	
	private long userId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String email;
	private String mobile;
	private String school;
	private String college;
	private String city;
	private String state;
	private String pincode;
	private String country;
	//user is active or blocked
	private String status;
	//user or admin 
	private String role;
	
}
