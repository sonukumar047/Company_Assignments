package com.masai.service;

import java.util.List;

import com.masai.model.UserProfile;

public interface UserService {
	UserProfile createUserProfile(UserProfile userProiile);
	List<UserProfile> getAllUserPrifile();
	UserProfile getUserById(Long UserId);
	UserProfile updateUserById(Long userId, UserProfile userProfile);
	String deleteUserById(Long userId);

}
