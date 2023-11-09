package com.example.friendfinder.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserInputDto {

	@NotNull(message="user firstname should not be null")
	@NotEmpty(message="user firstname should not be empty")
	@NotBlank(message="user firstname should not be blank")
	private String firstName;
	
	private String lastName;
	private LocalDate dob;
	
	@Email(message="email id should be of the format xxxx@xxx.xx")
	private String email;
	
	@Pattern(regexp="[0-9]{10}",message="enter valid mobile number of 10 digits")
	private String mobile;
	
	private String school;
	private String college;
	
	private String username;
	
	private String password;
	
	//user is active or blocked
	@NotNull(message="user status should be either active or blocked")
	@NotEmpty(message="user status should be either active or blocked")
	@NotBlank(message="user status should be either active or blocked")
	private String status;
	
	//user or admin 
	@NotNull(message="role should be either user or admin")
	@NotEmpty(message="role should be either user or admin")
	@NotBlank(message="role should be either user or admin")
	private String role;
	
	private String city;
	private String state;
	private String pincode;
	private String country;
	
}
