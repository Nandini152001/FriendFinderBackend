package com.example.friendfinder.entity;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "users")
public class User implements Serializable{

	@Id
    @GeneratedValue
    private long userId;
    
    @NotEmpty(message= "First name must not Empty.")
    @NotNull(message="First name must not null.")
    @NotBlank(message="First name must not Blank.")
    private String firstName;
    
    private String lastName;
    private LocalDate dob;
    private String email;
    private String mobile;
    private String school;
    private String college;
    private String username;
    private String password;
    //user is active or blocked
    private String status;
    //user or admin
    private String role;
    
    //Relationships
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_address_fk")
    private Address address;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_sender_fk")
    private List<FriendRequest> sentRequest;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_receiver_fk")
    private List<FriendRequest> receivedRequest;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_posts_fk")
    private List<Posts> posts;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_complaint_fk")
    private List<Complaint> complaint;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_comments_fk")
    private List<Comment> comments;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_likes_fk")
    private List<Likes> likes;

   @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_user_id"))
    private List<User> friends = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    Set<SelectedOption> id;
    
	
}