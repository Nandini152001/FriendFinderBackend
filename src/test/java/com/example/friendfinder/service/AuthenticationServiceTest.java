package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.friendfinder.dto.LoginInputDto;
import com.example.friendfinder.dto.LoginOutputDto;
import com.example.friendfinder.dto.UserInputDto;
import com.example.friendfinder.dto.UserOutputDto;
import com.example.friendfinder.exceptions.UserNotFoundException;

@SpringBootTest
class AuthenticationServiceTest {

	@Autowired
	IAuthenticationService authServ;
	
	//testing user registration 
	@Disabled
	@Test
	void registerTest()
	{
		//creating user dto object
		UserInputDto newUser = new UserInputDto();
		newUser.setFirstName("Abhishek");
		
		//storing userInputDto values to userOutputDto
		UserOutputDto userDto = authServ.register(newUser);
		
		//validation
		assertEquals("Abhishek",userDto.getFirstName());
	}
	
	
	@Test
	void loginTest()
	{
		LoginInputDto login = new LoginInputDto();
		login.setEmail("vivek@gmail.com");
		login.setPassword("v@suresh18");
		login.setRole("User");
		//login.setStatus("Active");
		
		LoginOutputDto logDto = authServ.login(login);
		assertEquals("vivek@gmail.com",logDto.getEmail());
	}
	
	
	@Test
	void logoutTest()
	{
		String email = "akash@gmail.com";
		String dto = authServ.logout(email);
		
		assertEquals("logout successfull",dto);
		
	}
	
	@Test
	void UserNotFoundExceptionTest()
	{
		assertThrows(UserNotFoundException.class, () -> {
			authServ.logout("abhi@gmail.com");
		});
			
	}
	
	@Test
	void InvalidCredentialsExceptionTest()
	{
		assertThrows(UserNotFoundException.class, () -> {
			authServ.logout("abhi@gmail.com");
		});
	}

}
