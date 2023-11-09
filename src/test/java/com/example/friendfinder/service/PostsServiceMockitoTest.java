package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.friendfinder.entity.Posts;
import com.example.friendfinder.repository.IPostRepository;

@ExtendWith(SpringExtension.class)
class PostsServiceMockitoTest {
/*
	// @InjectMock - Creates instance of a class and injects mocks that are created
		// with @Mock

		@InjectMocks
		PostServiceImpl postServ;

		// @MockBean - Creates Mock of a class or interface
		@MockBean
		IPostRepository postRepo;

		// Initialization of mock objects
		@BeforeEach
		void init() {
			MockitoAnnotations.openMocks(this);
		}
		
//		Test getting post by Id
//		database returns optional object same thing we are returning here using then method
		@Test
		void getPostByIdTest() {
			Posts post = new Posts();
		    post.setPostId(34);
	        post.setText("This is a new post");
//		    returning same response as we were used to return with database
			Mockito.when(postRepo.findById((long)34)).thenReturn(Optional.of(post));
			Posts response = postServ.getPostById(34);
			assertEquals(34,response.getPostId());
			assertEquals("This is a new post",response.getText());
		}
	
//		Test get all posts
		@Test
		void getAllPostsTest() {
			Posts post = new Posts();
			post.setPostId(1000);
			post.setText("I am gonna solve it today!");
			
			Posts post1 = new Posts();
			post.setPostId(1001);
			post.setText("Ok!I will help you out");
			
			List<Posts>postList = new ArrayList<>();
			postList.add(post);
			postList.add(post1);
			
			Mockito.when(postRepo.findAll()).thenReturn(postList);
			List<Posts> postLST = postServ.getAllPosts();
			assertEquals(2,postLST.size());
			assertEquals("Ok!I will help you out",postLST.get(0).getText());
		}

//		Test create new Post
		@Test
		void createPostTest() {
			Posts post = new Posts();
			post.setPostId(100);
			post.setText("New Post");
			
			Mockito.when(postRepo.save(post)).thenReturn(post);
			Posts newPost = postServ.createPost(post);
			assertEquals(100,newPost.getPostId());
			assertEquals("New Post", post.getText());
		}
		
//		Test update post by postId
		@Test
		void updatePostTest() {
			Posts post = new Posts();
			post.setPostId(1000);
			post.setText("I will solve this bug by tomorrows EOD");
			Mockito.when(postRepo.findById((long)1000)).thenReturn(Optional.of(post));
			Mockito.when(postRepo.save(post)).thenReturn(post);
			
			Posts updatedPost = postServ.updatePost((long)1000, post);
			assertEquals((long)1000,updatedPost.getPostId());
			assertEquals("I will solve this bug by tomorrows EOD",updatedPost.getText());
		}
		
//		Test delete post by id
		void deletePostByIdTest() {
			Posts oldPost = new Posts();
			oldPost.setPostId(1000);
			oldPost.setText("I will solve this bug by tomorrows EOD");
			oldPost.setPostedOn(LocalDateTime.now());
			oldPost.setLikes(null);
			oldPost.setComments(null);
			Posts response = postServ.deletePostById(1000);
			assertEquals(1000, response.getPostId());
			assertEquals("I will solve this bug by tomorrows EOD",response.getText());
			assertEquals(null, response.getComments());	

		}
		
//		void addCommentTest() {
//			Posts post = new Posts();
//			Comment comm =new Comment();
//			post.setComments(null);
//			List<Comment>comment = postServ.addComment(comm, 100);
//			Mockito.when(postRepo.findById((long)100)).thenReturn(Optional.of(post));
//			Mockito.when(postRepo.save(post)).thenReturn(comm);
//			
//			
//		}
		
		
		
		
		
		*/
}
