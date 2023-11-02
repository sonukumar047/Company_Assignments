package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Review;
import com.masai.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping(value = "/createReview/{productId}")
	public ResponseEntity<Review> createReview(@RequestBody Review review, @PathVariable("productId") Integer productId){
		Review createReview = reviewService.createReview(review, productId);
		return new ResponseEntity<Review>(createReview, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/reviewById/{productId}")
	public ResponseEntity<Review> getReviewById(@PathVariable("productId") Integer productId){
		Review reviewById = reviewService.getReviewById(productId);
		return new ResponseEntity<Review>(reviewById, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/allReviews")
	public ResponseEntity<List<Review>> getAllReview(){
		List<Review> allReviews = reviewService.getAllReviews();
		return new ResponseEntity<List<Review>>(allReviews, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteReview/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable("reviewId") Integer reviewId){
		reviewService.deleteReviewById(reviewId);
		return new ResponseEntity<String>("Review deleted", HttpStatus.OK);
	}
}
