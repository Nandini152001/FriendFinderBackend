package com.example.friendfinder.service;

public interface IAdminService {
	// Methods to block and unblock users by admin
	void unblockUserAccount(Long userId);
	void blockUserAccount(Long userId);
	
}
