package com.example.friendfinder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository adminRepo;	// adding dependencies for admin interface repository
	
	//implementing method to unblock a user
	@Override
	public void unblockUserAccount(Long userId) {
		Optional<User> opt=adminRepo.findById(userId);	// getting entity for primary key passed
		if(opt.isPresent()) {
			User dbUser=opt.get();	 // getting user from the database
			dbUser.setStatus("Active");	// setting user status
			adminRepo.save(dbUser);	// saving user to the database
		}
		else {
			throw new UserNotFoundException("Invalid user: User not found");	// throwing user not found exception
		}
	}

	//implementing method to block a user
	@Override
	public void blockUserAccount(Long userId) {
		Optional<User> opt=adminRepo.findById(userId);	// getting entity for primary key passed
		if(opt.isPresent()) {
			User dbUser=opt.get();	 // getting user from the database
			dbUser.setStatus("Blocked");	// setting user status
			adminRepo.save(dbUser);	// saving user to the database
		}
		else {
			throw new UserNotFoundException("Invalid user: User not found");	// throwing user not found exception
		}
	}
}
