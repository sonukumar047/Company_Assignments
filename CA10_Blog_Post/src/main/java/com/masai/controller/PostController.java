package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Post;
import com.masai.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping(value = "/addPost")
	public ResponseEntity<Post> savePost(@RequestBody Post post){
		return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getPostById/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Integer postId){
		return new ResponseEntity<Post>(postService.getPostById(postId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getAllPost")
	public ResponseEntity<List<Post>> getAllPost(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return new ResponseEntity<List<Post>>(postService.getAllPost(page, size), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deletePostById/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable("postId") Integer postId){
		return new ResponseEntity<String>(postService.deletePostById(postId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/modifyPost/{postId}")
	public ResponseEntity<String> modifyPostByPostId(@PathVariable("postId") Integer postId){
		return new ResponseEntity<String>(postService.modifyPostBodyById(postId), HttpStatus.OK);
	}
}
