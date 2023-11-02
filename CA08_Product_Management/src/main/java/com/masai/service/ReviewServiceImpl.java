package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Product;
import com.masai.model.Review;
import com.masai.repository.ProductRepository;
import com.masai.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductRepository productRepository;

	
	@Override
	public Review createReview(Review review, Integer productId) {
		
		Product product = productRepository.findById(productId).get();
		review.setProduct(product);
		return reviewRepository.save(review);
	}

	@Override
	public Review getReviewById(Integer reviewId) {
		Optional<Review> optional = reviewRepository.findById(reviewId);
		return optional.get();
	}

	@Override
	public List<Review> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll();
		return reviews;
	}

	@Override
	public String deleteReviewById(Integer reviewId) {
		reviewRepository.deleteById(reviewId);
		return "Review deleted Successfully";
	}

}
