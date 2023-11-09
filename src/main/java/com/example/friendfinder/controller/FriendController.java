package com.example.friendfinder.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friendfinder.dto.FriendInputDto;
import com.example.friendfinder.dto.FriendOutputDto;
import com.example.friendfinder.entity.FriendRequest;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.service.IFriendService;

@RestController	// annotation to work with controller
@CrossOrigin
public class FriendController {

	@Autowired
	IFriendService friendServ;	// adding dependencies for friend service interface 
	
	//Create logger obj
    private Logger logger = LogManager.getLogger();
	
    @PostMapping("/friend/send")    // annotation to add data in the database, sending friend request
    ResponseEntity<FriendOutputDto> sendFriendRequest(@Valid @RequestBody FriendInputDto req) {    //Request data from body of variable
        logger.debug(req);
        FriendOutputDto sendRequest=friendServ.sendFriendRequest(req);    // calling service method to send a friend request
        return new ResponseEntity<>(sendRequest,HttpStatus.CREATED);    // return responseEntity
    }
    
    @PostMapping("/friend/accept")    // annotation to add data in the database, accepting friend request
    ResponseEntity<FriendOutputDto> acceptFriendRequest(@Valid @RequestBody FriendInputDto req){
        FriendOutputDto acceptRequest=friendServ.acceptFriendRequest(req);    // calling service method to accept a friend request
        return new ResponseEntity<>(acceptRequest,HttpStatus.CREATED);    // return responseEntity
    }
    
    @GetMapping("/friend/getAllReceivedRequest/{userId}")    // annotation to get data from the database, getting list of sent friend request by userId
    ResponseEntity<List<FriendRequest>> viewAllReceivedFriendRequest(@Valid @PathVariable long userId) {    //PathVariable read data from URL
        List<FriendRequest> friends=friendServ.viewAllReceivedFriendRequest(userId);    // calling service method to get list of sent friend request by userId
        return new ResponseEntity<>(friends,HttpStatus.OK);    // return responseEntity
    }
    
    @GetMapping("/friend/getUsersBy/firstName/{firstName}")    // annotation to get data from the database, getting friends by firstName
    ResponseEntity<List<User>> searchUserByFirstName(@Valid @PathVariable String firstName){
        List<User> friends=friendServ.searchUserByFirstName(firstName);    // calling service method to get friends by firstName
        return new ResponseEntity<>(friends, HttpStatus.OK);    // return responseEntity
    }
    
    @GetMapping("/friend/getAllFriendsBy/{userId}")    // annotation to get data from the database, getting friendList by userId
    ResponseEntity<List<User>> viewAllFriends(@Valid @PathVariable long userId) {
        List<User> lst=friendServ.viewAllFriends(userId);    // calling service method to get friendList by userId
        return new ResponseEntity<>(lst,HttpStatus.OK);    // return responseEntity
    }
    
    @GetMapping("/friend/getUserBy/{userId}")
    ResponseEntity<User> getUserById(@Valid @PathVariable long userId) {
        User newUser = friendServ.getUserById(userId);
        return new ResponseEntity<>(newUser,HttpStatus.OK);    // return responseEntity
    }
    
    @GetMapping("/friend/getAllUsers")
    ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = friendServ.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);    // return responseEntity
    }
}
