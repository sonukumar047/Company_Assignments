package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BlogNotFound;
import com.masai.model.Blog;
import com.masai.model.Review;
import com.masai.repository.BlogRepository;
import com.masai.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private BlogRepository blogRepository;


	@Override
	public Review createReview(Integer blogId, Review review) {
		
		Optional<Blog> blogOptional = blogRepository.findById(blogId);

	    if (blogOptional.isPresent()) {
	        Blog blog = blogOptional.get();
	        review.setBlog(blog); // Set the blog for the review
	        blog.getReviews().add(review); // Add the review to the blog's list of reviews
	        blogRepository.save(blog); // Save the blog to update the relationship
	        return reviewRepository.save(review); // Save and return the review
	    } else {
	        throw new BlogNotFound("Blog not found with id: " + blogId);
	    }
	}

	@Override
	public Review getReviewById(Integer reviewId) {
		
		Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        if (reviewOptional.isPresent()) {
            return reviewOptional.get();
        } 
        else throw new BlogNotFound("Review not found with id: " + reviewId); 
		 
	}

	@Override
	public List<Review> getAllReviews() {
	
		try {
			return reviewRepository.findAll();
		} catch (Exception e) {
			throw new BlogNotFound("Failed to retrive reviews from the database."+e);
		}
	}

	@Override
	public String deleteReviewById(Integer reviewId) {

		Optional<Review> optionalReview = reviewRepository.findById(reviewId);
		
		if(optionalReview.isPresent()) {
			reviewRepository.deleteById(reviewId);
			return "Review deleted successfully!";
		} 
		else throw new BlogNotFound("Review not found with Id:"+reviewId);
	}

	

}
