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

import com.masai.model.Review;
import com.masai.service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping(value = "/addreview/{blogId}")
	public ResponseEntity<Review> addReview(@PathVariable("blogId") Integer blogId,@RequestBody Review review){
		return new ResponseEntity<Review>(reviewService.createReview(blogId,review), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/reviewById/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable("reviewId") int reviewId){
		return new ResponseEntity<Review>(reviewService.getReviewById(reviewId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/allReviews")
	public ResponseEntity<List<Review>> getAllReviews(){
		return new ResponseEntity<List<Review>>(reviewService.getAllReviews(),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteById/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable("reviewId") Integer reviewId){
		return new ResponseEntity<String>(reviewService.deleteReviewById(reviewId), HttpStatus.OK);
	}
}
