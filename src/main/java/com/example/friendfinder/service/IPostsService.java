package com.example.friendfinder.service;

import java.util.List;

import com.example.friendfinder.dto.PostsInputDto;
import com.example.friendfinder.entity.Comment;
import com.example.friendfinder.entity.Likes;
import com.example.friendfinder.entity.Posts;
import com.example.friendfinder.entity.User;


public interface IPostsService {
	
	    PostsInputDto createPost(PostsInputDto post);
	    List<Comment> addComment(Comment comment, long postId); //in comment interface
	    boolean likePost(Likes like,long postId);
	    Posts deletePostById(long postId);
	    Posts updatePost(long postId, PostsInputDto post);
	    List <Posts> getAllPosts();
	    String deleteComment(long commentId);
	    Posts getPostById(long postId);
//	    List<Posts> viewPostByUserId (long user_Id);
	    List<Comment> viewComments (long postId);
	    PostsInputDto getOnlyPost(long postId);
	    PostsInputDto updatePostOnly (PostsInputDto post);
	    List<Posts> viewPostByUserId (long user_Id);
	    User addPostByUserId(long userId, String text);

}
