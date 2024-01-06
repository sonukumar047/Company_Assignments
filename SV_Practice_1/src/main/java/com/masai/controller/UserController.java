package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.UserProfile;
import com.masai.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile){
		return new ResponseEntity<UserProfile>(userService.createUserProfile(userProfile), HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserById/{userId}")
	ResponseEntity<UserProfile> getUserById(@PathVariable("userId") Long userId){
		return new ResponseEntity<UserProfile>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	ResponseEntity<List<UserProfile>> getAllUser(){
		return new ResponseEntity<List<UserProfile>>(userService.getAllUserPrifile(), HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	ResponseEntity<UserProfile> updateUser(@PathVariable("userId") Long userId, @RequestBody UserProfile userProfile){
		return new ResponseEntity<UserProfile>(userService.updateUserById(userId, userProfile), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	ResponseEntity<String> deleteUserById(@PathVariable("userId") Long userId){
		return new ResponseEntity<String>(userService.deleteUserById(userId), HttpStatus.OK);
	}
}
