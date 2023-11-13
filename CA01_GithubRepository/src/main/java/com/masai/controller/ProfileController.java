package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Profile;
import com.masai.service.ProfileService;

@RestController
@CrossOrigin("*")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<Profile> getProfile(@PathVariable("username") String username){
		return new ResponseEntity<Profile>(profileService.getProfile(username), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/saveUsers/{username}")
	public ResponseEntity<Profile> saveProfile(@PathVariable("username") String username){
		return new ResponseEntity<Profile>(profileService.saveProfile(username), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/usersById/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable("id") String id){
		return new ResponseEntity<Profile>(profileService.getSavedProfile(id), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/allUsers")
	public ResponseEntity<List<Profile>> getAllSavedProfile(){
		return new ResponseEntity<List<Profile>>(profileService.getAllSavedFrofile(), HttpStatus.ACCEPTED);
	}
}
