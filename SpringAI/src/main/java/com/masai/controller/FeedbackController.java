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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.UserFeedback;
import com.masai.exception.FeedbackException;
import com.masai.service.FeedbackService;

@CrossOrigin("*")
@RestController
@RequestMapping("/feedback")

public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/addfeedback")
	public ResponseEntity<String> provideFeedback(@RequestBody String userFeedback) {
		try {
			// Save user feedback using the service
			feedbackService.saveFeedback(userFeedback);

			return ResponseEntity.ok("Feedback received and saved successfully");
		} catch (FeedbackException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving feedback: " + e.getMessage());
		}
	}
	
	@GetMapping("/getAllFeedback")
	public ResponseEntity<List<UserFeedback>> getAllFeedback(){
		return new ResponseEntity<List<UserFeedback>>(feedbackService.getAllFeedback(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteFeedback/{feedbackId}")
	public ResponseEntity<String> deleteFeedbackById(@PathVariable("feedbackId") int feedbackId){
		return new ResponseEntity<String>(feedbackService.deleteFeedback(feedbackId), HttpStatus.OK);
	}
	
	@PutMapping("/updateFeedback/{feedbackId}")
	public ResponseEntity<String> updateFeedbackById(@PathVariable("feedbackId") int feedbackId, @RequestBody String feedback){
		feedbackService.updateFeedback(feedbackId, feedback);
		return new ResponseEntity<String>("Feedback updated successfully!", HttpStatus.ACCEPTED);
	}
	
}
