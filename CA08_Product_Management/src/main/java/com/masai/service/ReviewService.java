package com.masai.service;

import java.util.List;

import com.masai.model.Review;

public interface ReviewService {

	Review createReview(Review review, Integer productId);
	Review getReviewById(Integer reviewId);
	List<Review> getAllReviews();
	String deleteReviewById(Integer reviewId);
}
