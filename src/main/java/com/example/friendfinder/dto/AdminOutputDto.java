package com.example.friendfinder.dto;

import java.time.LocalDateTime;
import com.example.friendfinder.entity.Complaint.Status;

import lombok.Data;
@Data
public class AdminOutputDto {
	
	
	private long complaintId;
	private String complaintText;
	private LocalDateTime dateTime;
	private Status status;
	private String adminFeedback;
}