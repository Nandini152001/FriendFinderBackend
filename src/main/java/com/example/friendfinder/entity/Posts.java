package com.example.friendfinder.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Posts {

	@Id
	@GeneratedValue
	private long postId;

	private String text;
	private LocalDateTime postedOn;
	
	//Relations 
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="post_comments_fk")
	private List<Comment> comments;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="post_likes_fk")
	private List<Likes> likes;
}

