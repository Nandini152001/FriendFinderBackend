package com.example.friendfinder.entity;

import javax.persistence.EmbeddedId;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_polls")
@Data
public class SelectedOption {

	  //the compound id shall be instansiated
	   @EmbeddedId
	   SelectedOptionKey optionId=new SelectedOptionKey();
	   
	    @ManyToOne
	    @MapsId("userId")
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @MapsId("pollId")
	    @JoinColumn(name = "poll_id")
	    private Polls poll;
	    
	    private String selected_option;
}








