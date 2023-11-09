package com.example.friendfinder.dto;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginInputDto {

	@Email(message="email id should be of the format xxxx@xxx.xx")
	private String email;
	
	//private String username;
	
	private String password;
	
	@NotNull(message="role should be either user or admin")
	@NotEmpty(message="role should be either user or admin")
	@NotBlank(message="role should be either user or admin")
	private String role;
}
