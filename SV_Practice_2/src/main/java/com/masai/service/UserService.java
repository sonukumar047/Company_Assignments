package com.masai.service;

import java.util.List;

import com.masai.model.UserProfile;

public interface UserService {

	UserProfile createUser(UserProfile userProfile);
	List<UserProfile> getAllUsers();
	UserProfile getUserById(Long userId);
	UserProfile updateUser(Long userId, UserProfile userProfile);
	String deleteUser(Long userId);
}
