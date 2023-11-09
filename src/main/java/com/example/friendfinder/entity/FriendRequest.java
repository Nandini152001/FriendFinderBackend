package com.example.friendfinder.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Data
//@Table(name="friend_request")
public class FriendRequest {

	 @Id
	    @GeneratedValue
	    private long friendRequestId;
	    
	    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	    @OneToOne(cascade=CascadeType.PERSIST)
	    @JoinColumn(name="user_sender_fk")
	    private User from;
	    
	    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	    @OneToOne(cascade=CascadeType.PERSIST)
	    @JoinColumn(name="user_receiver_fk")
	    private User to;
	    
	    private LocalDate dateOfRequest;
	    
	    @NotEmpty(message= "Meassage must not Empty.")
	    @NotNull(message= "Meassage must not Null.")
	    @NotBlank(message= "Meassage must not Blank.")
	    private String message;
}
