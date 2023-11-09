package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.friendfinder.entity.Posts;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostsServiceTest {

	@Autowired
	IPostsService postServ;
	
	@Test
//	grouping
	@Tag("get")
	@Order(1)
	void createPostTest() {
		Posts post = postServ.getPostById(34);
		String str = post.getText();
		assertEquals("Hi everybody",str);
	}
	
	@Test
//	grouping
	@Tag("get")
    @Order(2)
	void getAllPostsTest() {
		List<Posts>postList = postServ.getAllPosts();
		int totalPosts = postList.size();
		assertEquals(3,totalPosts);
		Posts post = postList.get(0);
		assertEquals("New Post I am adding",post.getText());
		Posts post1 = postList.get(1);
		assertEquals("Hi everybody",post1.getText());
	}
	
    @Disabled
	@Test
	void deletePostByIdTest() {
		Posts dltPost = null;
		dltPost = postServ.deletePostById(42);
		assertEquals("Creating this post for deletion testing",dltPost.getText());
//		assertEquals("Ok! perform delete operation",dltPost.getComments());
	}
}
	
   
//    @Test
//    @Order(3)
//    void addCommentTest() {
//    	Comment comment = new Comment();
//        comment.setCommentText("A very pleasant good morning!");
