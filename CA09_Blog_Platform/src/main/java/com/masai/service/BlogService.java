package com.masai.service;

import java.util.List;

import com.masai.model.Blog;

public interface BlogService {

	 Blog createBlog(Blog blog);
	 Blog getBogById(Integer BlogId);
	 List<Blog> getAllBlogs();
	 String deleteBlogById(Integer blogId);
	
}
