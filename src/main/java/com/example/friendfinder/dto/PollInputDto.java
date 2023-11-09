package com.example.friendfinder.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PollInputDto {

   // private long pollId;
    
    @NotNull(message="question should not be null")
	@NotEmpty(message="question should not be empty")
	@NotBlank(message="question should not be blank")
	private String question;
	
	private List<String> answers;
	
	private LocalDate createdOn;
}
