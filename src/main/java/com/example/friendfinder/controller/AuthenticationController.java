package com.example.friendfinder.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friendfinder.dto.LoginInputDto;
import com.example.friendfinder.dto.LoginOutputDto;
import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.dto.UserInputDto;
import com.example.friendfinder.dto.UserOutputDto;
import com.example.friendfinder.service.IAuthenticationService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class AuthenticationController {

	@Autowired
	IAuthenticationService authServ;
	
    //new user registration
	@PostMapping("/user/registration")
	ResponseEntity<UserOutputDto> register(@Valid @RequestBody UserInputDto user)
	{
		UserOutputDto newUser = authServ.register(user);
		return new ResponseEntity<>(newUser,HttpStatus.OK);
		
	};
	
	//login
	@PostMapping("/login")
	ResponseEntity<LoginOutputDto> login(@Valid @RequestBody LoginInputDto credentials)
	{
		LoginOutputDto newlogin = authServ.login(credentials);
		return new ResponseEntity<>(newlogin,HttpStatus.OK);
		
	};
	
	//logout
	@PostMapping("/logout/{emailId}")
	ResponseEntity<String> logout(@Valid @PathVariable String emailId)
	{
		String logout = authServ.logout(emailId);
		
		return new ResponseEntity<>(logout,HttpStatus.OK);
		
	};
}






