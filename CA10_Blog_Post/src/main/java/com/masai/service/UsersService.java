package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.model.UserLoginRequest;
import com.masai.model.Users;

public interface UsersService {

	Users registerUser(Users user);
	Users getUserByEmail(String email);
	List<Users> getAllUsers();
	Map<String, Object> login(UserLoginRequest request);
	String deleteUserById(int userId);
}
