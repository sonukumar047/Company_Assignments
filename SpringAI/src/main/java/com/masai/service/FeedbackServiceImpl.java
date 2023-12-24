package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.UserFeedback;
import com.masai.exception.FeedbackException;
import com.masai.repository.UserFeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private UserFeedbackRepository feedbackRepository;

	@Override
	public void saveFeedback(String userFeedback) {
		try {
            UserFeedback feedbackEntity = new UserFeedback();
            feedbackEntity.setFeedback(userFeedback);
            feedbackRepository.save(feedbackEntity);
        } catch (Exception e) {
            throw new FeedbackException("Error saving feedback");
        }
		
	}

	@Override
	public List<UserFeedback> getAllFeedback() {
		try {
			return feedbackRepository.findAll();
		} catch (Exception e) {
			throw new FeedbackException("Unable to get all the feedbacks");
		}
	}

	@Override
	public String deleteFeedback(int feedbackId) {
		feedbackRepository.deleteById(feedbackId);
		return "Feedback deleted successfully!";
	}

	@Override
	public void updateFeedback(int feedbackId, String updatedFeedback) {
		Optional<UserFeedback> optionalFeedback = feedbackRepository.findById(feedbackId);

        optionalFeedback.ifPresent(feedback -> {
            feedback.setFeedback(updatedFeedback);
            feedbackRepository.save(feedback);
        });
		
	}

}
