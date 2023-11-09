package com.example.friendfinder.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Complaint {
	
	  public enum Status {
	        QUERY_RAISED, QUERY_RESOLVED;
	    }

	   @Id
	    @GeneratedValue
	    private long complaintId;
	    
	    @Pattern(regexp="[a-zA-Z]+[a-zA-Z\\s]*[a-zA-Z]*", message = "Commlaint should be only typed using alphabate")
	    @NotEmpty(message= "Complaint message must not Empty.")
	    @NotNull(message="Complaint message must not null.")
	    @NotBlank(message="Complaint messge must not Blank.")
	    private String complaintText;
	    @UpdateTimestamp
	    private LocalDateTime registeredOn;
	    //complaint is new or resolved
	    @Enumerated(EnumType.ORDINAL)
	    private Status status;
	    
	    @NotEmpty(message= "Comment must not Empty.")
	    @NotNull(message="Comment must not null.")
	    @NotBlank(message="Comment messge must not Blank.")
	    private String comments;
	    
	    @OneToOne(cascade=CascadeType.ALL)
	    @JoinColumn(name="post_reported_fk")
	    private Posts post;
	    
	    @OneToOne(cascade=CascadeType.ALL)
	    @JoinColumn(name="postComment_reported_fk")
	    private Comment postComment;
	    
//	    @ManyToOne
//	    User user;
}



	

	


