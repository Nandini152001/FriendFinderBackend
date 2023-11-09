package com.example.friendfinder.dto;

import lombok.Data;

@Data
public class ParticipatePollDto {

	private long userId;
	private long pollId;
	private String option;
	
}
