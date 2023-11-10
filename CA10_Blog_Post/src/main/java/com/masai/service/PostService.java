package com.masai.service;

import java.util.List;

import com.masai.model.Post;

public interface PostService {

	Post createPost(Post post);
	Post getPostById(int postId);
	List<Post> getAllPost(int page, int size);
	String deletePostById(int postId);
	String modifyPostBodyById(int postId);
}
