package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.UserProfile;
import com.masai.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	  @Autowired
	    private UserProfileRepository repository;

	    @Override
	    public UserProfile createUserProfile(UserProfile userProfile) {
	        userProfile.setCreatedAt(LocalDateTime.now());
	        return repository.save(userProfile);
	    }

	    @Override
	    public List<UserProfile> getAllUserProfiles() {
	        return (List<UserProfile>) repository.findAll();
	    }

	    @Override
	    public UserProfile getUserProfileById(Long id) {
	        Optional<UserProfile> optionalUserProfile = repository.findById(id);
	        return optionalUserProfile.orElse(null);
	    }

	    @Override
	    public UserProfile updateUserProfile(Long id, UserProfile userProfile) {
	        UserProfile existingUserProfile = getUserProfileById(id);

	        if (existingUserProfile != null) {
	            existingUserProfile.setUsername(userProfile.getUsername());
	            existingUserProfile.setEmail(userProfile.getEmail());
	            return repository.save(existingUserProfile);
	        } else {
	            return null; // Handle not found scenario
	        }
	    }

	    @Override
	    public void deleteUserProfile(Long id) {
	        repository.deleteById(id);
	    }

}
