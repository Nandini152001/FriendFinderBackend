package com.example.friendfinder.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FriendOutputDto {
	
	private String firstNameFrom;
	private String firstNameTo;
	private LocalDate dateOfRequest;
	private String message;
}
