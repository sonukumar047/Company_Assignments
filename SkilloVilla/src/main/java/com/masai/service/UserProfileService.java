package com.masai.service;

import java.util.List;

import com.masai.model.UserProfile;

public interface UserProfileService {
	
	UserProfile createUserProfile(UserProfile userProfile);

    List<UserProfile> getAllUserProfiles();

    UserProfile getUserProfileById(Long id);

    UserProfile updateUserProfile(Long id, UserProfile userProfile);

    void deleteUserProfile(Long id);

}
