package com.example.friendfinder.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.friendfinder.entity.Posts;

import lombok.Data;

@Data
public class PostsOutputDto {

	private long userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String mobile;
    private String school;
    private String college;
    private String username;
    private String status;
    private List<Posts> post;
}
