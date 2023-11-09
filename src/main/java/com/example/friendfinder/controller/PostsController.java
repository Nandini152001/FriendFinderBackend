package com.example.friendfinder.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friendfinder.dto.PostsInputDto;
import com.example.friendfinder.entity.Comment;
import com.example.friendfinder.entity.Likes;
import com.example.friendfinder.entity.Posts;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.service.IPostsService;

@RestController
@CrossOrigin
public class PostsController {
    
	@Autowired
    IPostsService postServ;

    //Create logger obj
    private Logger logger = LogManager.getLogger();

    @PostMapping("/posts/create")
    public ResponseEntity<PostsInputDto>createPost(@Valid @RequestBody PostsInputDto post){
        logger.debug(post);
        System.out.println(post);
        PostsInputDto newPost = postServ.createPost(post);
        System.out.println(newPost);
        logger.debug(newPost);
        return new ResponseEntity<>(newPost,HttpStatus.CREATED);
    }


    @PostMapping("/posts/addComment/{postId}")
    ResponseEntity<List<Comment>>addComment(@Valid @RequestBody Comment comment, @PathVariable long postId){
        List<Comment> newComment = postServ.addComment(comment, postId); 
        return new ResponseEntity<>(newComment,HttpStatus.CREATED);
    }

 

    
    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<Posts>deletePostById(@Valid @PathVariable long postId ){
        Posts post =postServ.deletePostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

//    Endpoint configuration to update the post using postId
    @PutMapping("/posts/update/{postId}")
    ResponseEntity<Posts> updatePost(@Valid @PathVariable("postId") long postId, @RequestBody PostsInputDto post) {
            Posts updatedPost = postServ.updatePost(postId,post);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/findAll")
    ResponseEntity<List<Posts>>getAllPosts(){
        List<Posts> postsList = postServ.getAllPosts();
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

//    @DeleteMapping("/Comment/delete/{postId}/{commentId}")
//    ResponseEntity<String> deleteComment(@Valid @PathVariable long commentId, @PathVariable long postId){
//        postServ.deleteComment(commentId,postId);
//        return new ResponseEntity<>("Comment Deleted",HttpStatus.OK);
//    }

    @DeleteMapping("/Comment/delete/{commentId}")
    ResponseEntity<String> deleteComment(@Valid @PathVariable long commentId){
        postServ.deleteComment(commentId);
        return new ResponseEntity<>("Comment Deleted",HttpStatus.OK);
    }

 

    @PutMapping("/posts/likes/{postId}")
    ResponseEntity<Likes> likePost(@Valid @RequestBody Likes like ,@PathVariable long postId){
        postServ.likePost(like, postId);
        return new ResponseEntity<>(like,HttpStatus.OK);
    }

    @GetMapping("/posts/get/{postId}")
    ResponseEntity<Posts> getPostById(@Valid @PathVariable long postId){
        Posts post = postServ.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

//    @GetMapping("/posts/view/{userId}")
//    ResponseEntity<List<Posts>> viewPostByUserId(@Valid @PathVariable long userId){
//        List<Posts> postsList = postServ.getAllPosts();
//        return new ResponseEntity<>(postsList, HttpStatus.OK);
//    }

    @GetMapping("/posts/get/dto/{postId}")
    ResponseEntity<PostsInputDto> getOnlyPost(@Valid @PathVariable long postId){
        PostsInputDto post = postServ.getOnlyPost(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/posts/update/dto")
    ResponseEntity<PostsInputDto> updatePostOnly(@Valid @RequestBody PostsInputDto post) {
            PostsInputDto updatedPost = postServ.updatePostOnly( post);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/viewComments/{postId}")
    ResponseEntity<List<Comment>> viewComments(@Valid @PathVariable long postId){
        List<Comment> commentList = postServ.viewComments(postId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping("/posts/add/{userId}")
    public ResponseEntity<User>addPostByUserId(@Valid @PathVariable long userId,@RequestBody String text){
        User newUser = postServ.addPostByUserId(userId,text);
        System.out.println(newUser);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }
    
    @GetMapping("/posts/view/{userId}")
    ResponseEntity<List<Posts>> viewPostByUserId(@Valid @PathVariable long userId){
        List<Posts> postsList = postServ.viewPostByUserId(userId);
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

 
}
