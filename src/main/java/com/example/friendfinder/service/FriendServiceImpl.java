package com.example.friendfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.friendfinder.dto.FriendInputDto;
import com.example.friendfinder.dto.FriendOutputDto;
import com.example.friendfinder.entity.FriendRequest;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.IFriendRepository;
import com.example.friendfinder.repository.IUserRepository;

@Service
public class FriendServiceImpl implements IFriendService {

	@Autowired
	IUserRepository userRepo;	// adding dependencies for user interface repository
	
	@Autowired
	IFriendRepository friendRepo;	// adding dependencies for friend interface repository
	
	// Implementing method to send friend request
	@Override
    public FriendOutputDto sendFriendRequest(FriendInputDto req) {



       Optional<User> optFrom=userRepo.findById(req.getUserIdFrom()) ;    // return entity for the primary key passed
        Optional<User> optTo=userRepo.findById(req.getUserIdTo()) ;
        if(optFrom.isPresent() && optTo.isPresent()) {
            User dbUserFrom=optFrom.get();    // getting user from the database
            User dbUserTo=optTo.get();
            
            // converting FriendInputDto to FriendRequest
            FriendRequest fr=new FriendRequest();    // creating new FriendRequest object
            fr.setFrom(dbUserFrom);        // setting data fields
            fr.setTo(dbUserTo);
            fr.setDateOfRequest(req.getDateOfRequest());
            fr.setMessage(req.getMessage());
            
            List<FriendRequest> lstTo= dbUserTo.getReceivedRequest();    // getting list of send request
            lstTo.add(fr);    // adding request to list
            userRepo.save(dbUserTo);    // saving user to the database
                        
            // converting FriendRequest to FriendOutputDto
            FriendOutputDto outDto=new FriendOutputDto();    //  creating new FriendOutputDto object
            outDto.setFirstNameFrom(dbUserFrom.getFirstName());    // setting data fields
            outDto.setFirstNameTo(dbUserTo.getFirstName());
            outDto.setDateOfRequest(req.getDateOfRequest());
            outDto.setMessage(req.getMessage());
            
            return outDto; // returning outputDto
        }
        else {
            throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
        }
    }



   // Implementing method to accept friend request
    @Override
    @Transactional
    public FriendOutputDto acceptFriendRequest(FriendInputDto req) {
        
        Optional<User> optFrom=userRepo.findById(req.getUserIdFrom()) ;    // return entity for the primary key passed
        Optional<User> optTo=userRepo.findById(req.getUserIdTo()) ;
        if(optFrom.isPresent() && optTo.isPresent()) {
            User dbUserFrom=optFrom.get();    // getting user from the database
            User dbUserTo=optTo.get();
            
            // converting FriendInputDto to FriendRequest
            FriendRequest fr=new FriendRequest();    // creating new FriendRequest object
            fr.setFrom(dbUserFrom);    // setting data fields
            fr.setTo(dbUserTo);
            fr.setDateOfRequest(req.getDateOfRequest());
            fr.setMessage(req.getMessage());
            
            List<FriendRequest> lstTo= dbUserTo.getReceivedRequest();    // getting list of send request
            lstTo.remove(fr);    // removing request from list
            userRepo.save(dbUserTo);    // saving user to the database



           List<User> listOfFriendReceiver= dbUserTo.getFriends();    // getting friendList of a user
            listOfFriendReceiver.add(dbUserFrom);    // adding user to list
            userRepo.save(dbUserTo);    // saving user to the database
            
//            List<User> listOfFriendSender= dbUserFrom.getFriends();    // getting friendList of a user
//            listOfFriendSender.add(dbUserTo);    // adding user to list
//            userRepo.save(dbUserFrom);    // saving user to the database
                
            // converting FriendRequest to FriendOutputDto
            FriendOutputDto outDto=new FriendOutputDto();    //  creating new FriendOutputDto object
            outDto.setFirstNameFrom(dbUserFrom.getFirstName());    // setting data fields
            outDto.setFirstNameTo(dbUserTo.getFirstName());
            outDto.setDateOfRequest(req.getDateOfRequest());
            outDto.setMessage(req.getMessage());
            
            //deleting friend request using query
            friendRepo.deleteFriendRequestById(req.getUserIdFrom(),req.getUserIdTo());
            return outDto;    // returning outputDto
        }
        else {
            throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
        }
    }
    @Override
    public List<FriendRequest> viewAllReceivedFriendRequest(long userId) {
        
        Optional<User> opt=userRepo.findById(userId);    // return entity for the primary key passed
        if(opt.isPresent()) {
            User newUser=opt.get();
            return newUser.getReceivedRequest();
        }
        else {
            throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
        }
    }



   // Implementing method to search list of all friends by name
    @Override
    public List<User> searchUserByFirstName(String firstName) {
        return userRepo.findByFirstName(firstName);    // returning list of users by name
    }



   // Implementing method to see friendList of a user
    @Override
    public List<User> viewAllFriends(long userId) {
        
        Optional<User> opt=userRepo.findById(userId);    // return entity for the primary key passed
        if(opt.isPresent()) {
            User user=opt.get();
            return user.getFriends();    // returning friendList of a user
        }
        else {
            throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
        }
    }



   // Implementing method to get details of a user
    @Override
    public User getUserById(long userId){
        
        Optional<User> opt=userRepo.findById(userId);    // return entity for the primary key passed
        User newUser=null;    
        if(opt.isPresent()) {
            newUser=opt.get();    // getting user from the database
        }
        else {
            throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
        }
        return newUser;     // returning details of a user
    }


   @Override
    public List<User> getAllUsers() {
        List<User> lst=userRepo.findAll();
        return lst;
    }
    
}

