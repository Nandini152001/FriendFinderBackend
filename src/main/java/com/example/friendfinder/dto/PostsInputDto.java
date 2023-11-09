package com.example.friendfinder.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostsInputDto {

	private long postId;
    private String text;
    private LocalDateTime postedOn;
}
