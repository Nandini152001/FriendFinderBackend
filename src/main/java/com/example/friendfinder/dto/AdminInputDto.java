package com.example.friendfinder.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.friendfinder.entity.Complaint.Status;

import lombok.Data;
@Data
public class AdminInputDto {
	@NotEmpty(message= "Comment must not Empty.")
	@NotNull(message="Comment must not null.")
	@NotBlank(message="Comment messge must not Blank.")
	private long complaintId;
	@NotEmpty(message= "Comment must not Empty.")
	@NotNull(message="Comment must not null.")
	@NotBlank(message="Comment messge must not Blank.")
	private Status status;
}
