package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.modal.Users;
import com.app.service.EmailService;
import com.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users users){
		System.out.println("SONU");
		log.info("inside Regoster before sendEmail");
		emailService.sendEmail(users);
		log.info("inside Regoster after sendEmail");
		return new ResponseEntity<Users>(userService.registerUsers(users),HttpStatus.ACCEPTED);
	}

	
}
