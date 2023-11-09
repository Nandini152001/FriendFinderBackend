package com.example.friendfinder.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentId;
	
	private String commentText;
	private LocalDateTime createdOn;
}
