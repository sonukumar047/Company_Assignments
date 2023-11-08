package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Blog;
import com.masai.service.BlogService;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@PostMapping(value = "/addBlog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
		return new ResponseEntity<Blog>(blogService.createBlog(blog), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getBlogById/{blodId}")
	public ResponseEntity<Blog> getBlogById(@PathVariable("blodId") Integer blogId){
		return new ResponseEntity<Blog>(blogService.getBogById(blogId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getAllBlog")
	public ResponseEntity<List<Blog>> getAllBlog(){
		return new ResponseEntity<List<Blog>>(blogService.getAllBlogs(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/geleteBlogById/{blogId}")
	public ResponseEntity<String> deleteBlogById(@PathVariable("blogId") Integer blogId){
		return new ResponseEntity<String>(blogService.deleteBlogById(blogId), HttpStatus.OK);
	}
}
