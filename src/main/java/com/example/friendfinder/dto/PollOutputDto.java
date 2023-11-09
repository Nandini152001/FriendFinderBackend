package com.example.friendfinder.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PollOutputDto {

	    private long pollId;
		
		private String question;
		
		private List<String> answers;
		
		private LocalDate createdOn;
}
