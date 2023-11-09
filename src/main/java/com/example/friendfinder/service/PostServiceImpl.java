package com.example.friendfinder.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friendfinder.dto.PostsInputDto;
import com.example.friendfinder.dto.PostsOutputDto;
import com.example.friendfinder.entity.Comment;
import com.example.friendfinder.entity.Likes;
import com.example.friendfinder.entity.Posts;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.CommentNotFound;
import com.example.friendfinder.exceptions.PostNotFoundById;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.ICommentRepository;
import com.example.friendfinder.repository.IPostRepository;
import com.example.friendfinder.repository.IUserRepository;



@Service
public class PostServiceImpl implements IPostsService{

	@Autowired
    IPostRepository postRepo;

    @Autowired
    ICommentRepository commentRepo;

    @Autowired
    IUserRepository userRepo;

//    creating new post
    @Override
    public PostsInputDto createPost(PostsInputDto post) {

//        convert from PostsInputDto to posts
        Posts postIn = new Posts();
        postIn.setPostId(post.getPostId());
        postIn.setText(post.getText());
        postIn.setPostedOn(post.getPostedOn());

//        saving the PostsInputDto values to posts
        Posts newPost = postRepo.save(postIn);

//        converting from posts to postsOutputDto
        PostsInputDto postOut = new PostsInputDto();
        postOut.setPostId(newPost.getPostId());
        postOut.setText(newPost.getText());
        postOut.setPostedOn(newPost.getPostedOn());

        return postOut;
    }

    @Override
    public List<Comment> addComment(Comment comment, long postId) {
        Optional<Posts>opt = postRepo.findById(postId);
        List<Comment> newComment = null;
        if(opt.isPresent()) {
                Posts post = opt.get(); 
                newComment = post.getComments();
                newComment.add(comment);
                post.setComments(newComment);
                postRepo.save(post);
               return newComment;

        }else {
            throw new PostNotFoundById("Post not found by post Id: " +postId);
        }
    }

 

    @Override
    public boolean likePost(Likes like, long postId) {
        Optional<Posts>opt = postRepo.findById(postId);
        Posts post = null;
        if(opt.isPresent()) {
            post = opt.get();
            List<Likes> newLike = post.getLikes();
            newLike.add(like);
            post.setLikes(newLike);
            postRepo.save(post);
            return newLike.add(like);
        }else {
            throw new PostNotFoundById("Post is unavailable at postId: " +postId);
        }
    }

 

    @Override
    public Posts deletePostById(long postId) {
        Optional<Posts>opt = postRepo.findById(postId);
        Posts post = null;
        if(opt.isPresent()) {
            post = opt.get();
            postRepo.deleteById(postId);
        }else {
            throw new PostNotFoundById("Post is not found by postId: " +postId);
        }
        return post;
    }

 

    @Override
    public Posts updatePost(long postId, PostsInputDto post) {

//    convert from PostsInputDto to posts
    Posts postIn = new Posts();
    postIn.setPostId(post.getPostId());
    postIn.setText(post.getText());
    postIn.setPostedOn(post.getPostedOn());

//    saving the PostsInputDto values to posts
    Posts newPost = postRepo.save(postIn);

        Optional<Posts>opt = postRepo.findById(postId);
        if(opt.isPresent()) {
//            update post
            postRepo.save(postIn);
        }else {
            throw new PostNotFoundById("Post is not found by postId: " +postId);
        }
        return postIn;
    }

 


    @Override
    public List<Posts> getAllPosts() {
         List<Posts> postsList = postRepo.findAll(); 
        return postsList;
    }

 

    @Override
    public String deleteComment(long commentId) {
        Optional<Comment>opt = commentRepo.findById(commentId);
        if(opt.isPresent()) {
              commentRepo.deleteById(commentId);
              return("Comment deleted");
        }else {
            throw new CommentNotFound("Comment is not found by commentId: " +commentId);
        }
    }

 

    @Override
    public Posts getPostById(long postId) {
        Optional<Posts>opt = postRepo.findById(postId);
        Posts post = null;
        if(opt.isPresent()) {
            post = opt.get();
            return post;
        }else {
            throw new PostNotFoundById("Post is not found with given id: " + postId);
        }

    }

 

//    @Override
//    public List<Posts> viewPostByUserId(long user_Id) {
//        Optional<User> opt = userRepo.findById(user_Id);
//        List<Posts> listOfPosts = new ArrayList<>();
//        if(opt.isPresent()) {
//            User user = opt.get();
//            listOfPosts = user.getPosts();
//        }
//        return listOfPosts;
//    }

 

    @Override
    public PostsInputDto getOnlyPost(long postId) {
        Optional<Posts>opt = postRepo.findById(postId);
        Posts post = null;
        if(opt.isPresent()) {
            post = opt.get();
//        converting from posts to postsOutputDto
            PostsInputDto postOut = new PostsInputDto();
            postOut.setPostId(post.getPostId());
            postOut.setText(post.getText());
            postOut.setPostedOn(post.getPostedOn());
            return postOut;
        }else {
            throw new PostNotFoundById("Post is not found with given id: " + postId);
        }

    }

 

    @Override
    public PostsInputDto updatePostOnly(PostsInputDto post) {
        Posts postIn = new Posts();
        postIn.setPostId(post.getPostId());
        postIn.setText(post.getText());
        postIn.setPostedOn(post.getPostedOn());

        Posts newPost = postRepo.save(postIn);

        PostsInputDto postOut = new PostsInputDto();
        postOut.setPostId(newPost.getPostId());
        postOut.setText(newPost.getText());
        postOut.setPostedOn(newPost.getPostedOn());

        return postOut;

    }

 

    @Override
    public List<Comment> viewComments(long postId) {
        Optional<Posts> opt = postRepo.findById(postId);
        List<Comment> listOfComments= new ArrayList<>();
        if(opt.isPresent()) {
            Posts post = opt.get();
            listOfComments = post.getComments();
        }
        return listOfComments;
    }

 

    @Override
    public User addPostByUserId(long userId, String text) {
        Optional<User> opt = userRepo.findById(userId);
        Posts post1 = new Posts();
        PostsOutputDto outDto = new PostsOutputDto();
        User user = new User();
        if(opt.isPresent()) {
            user = opt.get();
            post1.setText(text);
            post1.setPostedOn(LocalDateTime.now());
            List<Posts> listOfPosts = user.getPosts();
            listOfPosts.add(post1);
            user.setPosts(listOfPosts);
            userRepo.save(user);            
        }
        return user;
    }
    @Override
    public List<Posts> viewPostByUserId(long user_Id) {
        Optional<User> opt = userRepo.findById(user_Id);
//        List<Posts> listOfPosts = new ArrayList<>();
//        PostsInputDto obj = new PostsInputDto();
        if(opt.isPresent()) {
            User user = opt.get();
          return user.getPosts();
      }
      else {
          throw new UserNotFoundException("Invalid user: User not found");    // throwing user not found exception
      }
  }
}