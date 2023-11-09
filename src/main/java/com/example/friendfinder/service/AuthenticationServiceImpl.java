package com.example.friendfinder.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friendfinder.dto.LoginInputDto;
import com.example.friendfinder.dto.LoginOutputDto;
import com.example.friendfinder.dto.UserInputDto;
import com.example.friendfinder.dto.UserOutputDto;
import com.example.friendfinder.entity.Address;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.InvalidCredentialsException;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.IAuthenticationRepository;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	IAuthenticationRepository authRepo;
	
	
	//new user registration
	@Override
	public UserOutputDto register(UserInputDto userDto) {
		
		//convert from userInputDto to user 
		
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setDob(userDto.getDob());
		user.setSchool(userDto.getSchool());
		user.setCollege(userDto.getCollege());
		user.setMobile(userDto.getMobile());
		user.setUsername(userDto.getUsername());
		
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setStatus(userDto.getStatus());
		
		Address addr = new Address();
		addr.setCity(userDto.getCity());
		addr.setCountry(userDto.getCountry());
		addr.setPincode(userDto.getPincode());
		addr.setState(userDto.getState());
		
		user.setAddress(addr);
		
		//save all the records to user table 
		User newUser = authRepo.save(user);
		
		//converting from user to userOutputDto
		UserOutputDto useroutDto = new UserOutputDto();
		
		useroutDto.setUserId(newUser.getUserId());
		useroutDto.setCity(newUser.getAddress().getCity());
		useroutDto.setCountry(newUser.getAddress().getCountry());
		useroutDto.setPincode(newUser.getAddress().getPincode());
		useroutDto.setState(newUser.getAddress().getState());
		
		useroutDto.setEmail(newUser.getEmail());
		useroutDto.setRole(newUser.getRole());
		useroutDto.setStatus(newUser.getStatus());
		useroutDto.setFirstName(newUser.getFirstName());
		useroutDto.setLastName(newUser.getLastName());
		useroutDto.setSchool(newUser.getSchool());
		useroutDto.setCollege(newUser.getCollege());
		useroutDto.setDob(newUser.getDob());
		useroutDto.setMobile(newUser.getMobile());
		
		return useroutDto;
		
	}
	
	//user login
	@Override
	public LoginOutputDto login(LoginInputDto login) {
		
        Optional<User> opt= authRepo.findByEmail(login.getEmail());
        LoginOutputDto log = new LoginOutputDto() ;
        
        //check if given user email id is present
        if(opt.isPresent()) 
        {
            User user = opt.get();
           // String userStr = user.getUsername();
            String passStr = user.getPassword();
            String roleStr = user.getRole();
            String statStr = user.getStatus();
            String email = user.getEmail();
            System.out.println(statStr);
            long userId = user.getUserId();
            boolean isLogin = false;
            
            // checking if given credentials(username,password,role and status is active) are true
            if(login.getPassword().equals(passStr) && login.getRole().equals(roleStr) && user.getStatus().contentEquals("Active")) 
            {
                isLogin = true;
                log.setRole(roleStr);
                log.setEmail(email);
                log.setStatus(statStr);
                log.setLogin(isLogin);
                log.setUserId(userId);
                return log;
            }
            else
    		{
    			throw new InvalidCredentialsException("Invalid username,password,status or role");
    		}
        }
        else
    	{
    		throw new UserNotFoundException("Login id not available");
    	}       
	}
	
	//user logout
	@Override
	public String logout(String email) {
		
		Optional<User> opt= authRepo.findByEmail(email);
		User user = opt.get();
		
		//checking if given user email id is present and then allowing to logout
		if(opt.isPresent() && user.getStatus().contentEquals("Active"))
		{
			return "logout successfull";
		}
		else 
		{
			throw new UserNotFoundException("Login id not available");
		}
	}
}



