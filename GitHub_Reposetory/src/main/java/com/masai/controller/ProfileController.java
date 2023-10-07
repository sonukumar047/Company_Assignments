package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Profile;
import com.masai.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<Profile> getOwner(@PathVariable("username")String username){
		
		log.info("inside getOwner "+username);
		return new ResponseEntity<Profile>(profileService.getProfile(username), HttpStatus.ACCEPTED);
	}
}
