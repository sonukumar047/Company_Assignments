package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.UserProfile;
import com.masai.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserProfile createUser(UserProfile userProfile) {
		try {
			userProfile.setCreatedAt(LocalDateTime.now());
			userRepository.save(userProfile);
		} catch (Exception e) {
			throw new UserException("Unable to ceate user");
		}
		return null;
	}

	@Override
	public List<UserProfile> getAllUsers() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new UserException("Unable to find users");
		}
	}

	@Override
	public UserProfile getUserById(Long userId) {
		try {
			 return userRepository.findById(userId).get();
		} catch (Exception e) {
			throw new UserException("User not find with this Id :"+ userId);
		}
		
	}

	@Override
	public UserProfile updateUser(Long userId, UserProfile userProfile) {
		try {
			Optional<UserProfile> findById = userRepository.findById(userId);
			if(findById != null) {
				return userRepository.save(findById.get());
			}
		} catch (Exception e) {
			throw new UserException("Unalbe to update user");
		}
		return null;
	}

	@Override
	public String deleteUser(Long userId) {
		try {
			userRepository.deleteById(userId);
			return "User Deleted Successfully";
		} catch (Exception e) {
			throw new UserException("Unable to delete user");
		}
	}

}
