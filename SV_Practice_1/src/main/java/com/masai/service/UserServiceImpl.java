package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.UserProfile;
import com.masai.repository.UserProfileRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public UserProfile createUserProfile(UserProfile userProiile) {
		try {
			userProiile.setCreatedaAt(LocalDateTime.now());
			return userProfileRepository.save(userProiile);
		} catch (Exception e) {
			throw new UserException("Unable to create User");
		}
	}

	@Override
	public List<UserProfile> getAllUserPrifile() {
		try {
			userProfileRepository.findAll();
		} catch (Exception e) {
			throw new UserException("Users not found");
		}
		return null;
	}

	@Override
	public UserProfile getUserById(Long UserId) {
		try {
			 Optional<UserProfile> findById = userProfileRepository.findById(UserId);
			 return findById.get();
		} catch (Exception e) {
			throw new UserException("User Not Found By this Id"+UserId);
		}
	}

	@Override
	public UserProfile updateUserById(Long userId, UserProfile userProfile) {
		try {
			Optional<UserProfile> findById = userProfileRepository.findById(userId);
			if(findById != null) {
				return userProfileRepository.save(findById.get());
			}
		} catch (Exception e) {
			throw new UserException("Unable to update user");
		}
		return null;
	}

	@Override
	public String deleteUserById(Long userId) {
	
		try {
			Optional<UserProfile> findById = userProfileRepository.findById(userId);
			if(findById != null) {
				userProfileRepository.deleteById(userId);
				return "User Deleted Successfully";
			}
		} catch (Exception e) {
			throw new UserException("Unable to deleteuser");
		}
		return null;
	}

}
