package com.example.friendfinder.service;

import java.util.List;

import com.example.friendfinder.dto.FriendInputDto;
import com.example.friendfinder.dto.FriendOutputDto;
import com.example.friendfinder.entity.FriendRequest;
import com.example.friendfinder.entity.User;

public interface IFriendService {
	
	// Methods to implement friend module 
	FriendOutputDto sendFriendRequest(FriendInputDto req);
    FriendOutputDto acceptFriendRequest(FriendInputDto req);
    List<FriendRequest> viewAllReceivedFriendRequest(long userId);
    List<User> searchUserByFirstName(String firstName);
    List<User> viewAllFriends(long userId);
    User getUserById(long userId);
    List<User> getAllUsers();
}
