package com.masai.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.masai.exception.PostNotFoundException;
import com.masai.model.Post;
import com.masai.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post createPost(Post post) {
		try {
			return postRepository.save(post);
		} catch (Exception e) {
			throw new PostNotFoundException("Failed to create post! Please try again.");
		}
		
	}

	@Override
	public Post getPostById(int postId) {
		Optional<Post> postById = postRepository.findById(postId);
		if(postById.isPresent()) {
			return postById.get();
		}
		else throw new PostNotFoundException("Post not found with Id:"+postId);
	}

	@Override
	public List<Post> getAllPost(int page, int size) {
		try {
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Post> postPage = postRepository.findAll(pageRequest);
			return postPage.getContent();
		} catch (Exception e) {
			throw new PostNotFoundException("Error occurred during retriving posts.");
		}
		
	}

	@Override
	public String deletePostById(int postId) {
		Optional<Post> postById = postRepository.findById(postId);
		if(postById.isPresent()) {
			postRepository.deleteById(postId);
			return "Post deleted seccssfully!";			
		}
		else throw new PostNotFoundException("Post not found with Id:"+postId);
		
	}

	@Override
	public String modifyPostBodyById(int postId) {
	    Post post = postRepository.findById(postId).orElse(null);

	    if (post != null) {
	        String body = post.getBody();
	        Pattern pattern = Pattern.compile("\\b[Aa]\\w+\\b");
	        Matcher matcher = pattern.matcher(body);

	        StringBuffer modifiedBody = new StringBuffer();

	        while (matcher.find()) {
	            String word = matcher.group();
	            if (word.length() >= 3) {
	                String modifiedWord = word.substring(0, word.length() - 3) + "***";
	                matcher.appendReplacement(modifiedBody, modifiedWord);
	            } else {
	                // Log the information for debugging
	                System.out.println("Skipped modification for word: " + word + " (Length: " + word.length() + ")");
	            }
	        }
	        matcher.appendTail(modifiedBody);

	        post.setBody(modifiedBody.toString());
	        postRepository.save(post);
	        return "Post modified successfully!";
	    } else {
	        throw new PostNotFoundException("Post not found with Id:" + postId);
	    }
	}

}
