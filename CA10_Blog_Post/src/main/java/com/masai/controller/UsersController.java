package com.masai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.masai.model.UserLoginRequest;
import com.masai.model.Users;
import com.masai.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users user){
		return new ResponseEntity<Users>(usersService.registerUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getUserByEmail/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable("email") String email){
		System.out.println("Inside get user by email");
		return new ResponseEntity<Users>(usersService.getUserByEmail(email), HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers(){
		return new ResponseEntity<List<Users>>(usersService.getAllUsers(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value = "/deleteUserById/{userId}")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") int userId){
		return new ResponseEntity<String>(usersService.deleteUserById(userId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request){
		Map<String, Object> login = usersService.login(request);
		
		if(login != null)return ResponseEntity.ok(login);
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password!");
	}
}
