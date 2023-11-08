package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BlogNotFound;
import com.masai.model.Blog;
import com.masai.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepository blogRepository;

	@Override
	public Blog createBlog(Blog blog) {
		
		try {
			return blogRepository.save(blog);
		} catch (Exception e) {
			throw new BlogNotFound("Failed to create blog! Please try again.");
		}
	}

	@Override
	public Blog getBogById(Integer blogId) {
		
		Optional<Blog> findById = blogRepository.findById(blogId);
		
		if(findById.isPresent()) {
			return findById.get();
		} 
		else throw new BlogNotFound("Blog Not Found with Id:"+blogId);
		
	}

	@Override
	public List<Blog> getAllBlogs() {
		try {
			return blogRepository.findAll();
		} catch (Exception e) {
			throw new BlogNotFound("Error occured during retriving blogs");
		}
	}

	@Override
	public String deleteBlogById(Integer blogId) {
		Optional<Blog> findById = blogRepository.findById(blogId);
		
		if(findById.isPresent()) {
			blogRepository.deleteById(blogId);
			return "Blog deleted successfully!";
		} 
		else throw new BlogNotFound("Blog not found with this blogId:"+blogId);
	}

}
