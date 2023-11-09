package com.example.friendfinder.dto;

import lombok.Data;

@Data
public class LoginOutputDto {

	private long userId;
	private String email;
	private String role;
	private String status;
	boolean isLogin;
}
