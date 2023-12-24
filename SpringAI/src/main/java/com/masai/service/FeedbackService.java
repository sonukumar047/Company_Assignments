package com.masai.service;

import java.util.List;


import com.masai.entity.UserFeedback;

public interface FeedbackService {
	
	void saveFeedback(String userFeedback);
	
	
	List<UserFeedback> getAllFeedback();

    String deleteFeedback(int feedbackId);

    void updateFeedback(int feedbackId, String updatedFeedback);
    

}
