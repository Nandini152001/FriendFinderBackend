package com.example.friendfinder.service;

import com.example.friendfinder.dto.LoginInputDto;
import com.example.friendfinder.dto.LoginOutputDto;
import com.example.friendfinder.dto.UserInputDto;
import com.example.friendfinder.dto.UserOutputDto;

public interface IAuthenticationService {

	//abstract methods
	UserOutputDto register(UserInputDto user);
	LoginOutputDto login(LoginInputDto login);
	String logout(String email);
}
