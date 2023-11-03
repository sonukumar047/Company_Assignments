package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masai.exception.GlobalExceptionHandler;
import com.masai.exception.RepositoryNotFound;
import com.masai.model.Profile;
import com.masai.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public static String ownerUrl = "https://api.github.com/users/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Profile getProfile(String username) {
		
		ResponseEntity<Profile> profileResponse = restTemplate.getForEntity(ownerUrl+username, Profile.class);
		
		Profile profile = profileResponse.getBody();
		
		return profile;
	}

	@Override
	public Profile saveProfile(String username) {

		ResponseEntity<Profile> profileResponse = restTemplate.getForEntity(ownerUrl+username, Profile.class);
		
		Profile profile = profileResponse.getBody();
		
		return profileRepository.save(profile);
	}

	@Override
	public Profile getSavedProfile(String id) {
		
		return profileRepository.findById(id).orElseThrow(
				()-> new RepositoryNotFound("Profile not found!"));
	}

	@Override
	public List<Profile> getAllSavedFrofile() {
		List<Profile> profileList = profileRepository.findAll();
		
		if(!profileList.isEmpty()) {
			return profileList;
		}
		else throw new RepositoryNotFound("Profile not found!");
	}

}
