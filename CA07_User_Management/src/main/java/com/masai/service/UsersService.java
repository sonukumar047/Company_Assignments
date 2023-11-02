package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.model.UserLoginRequest;
import com.masai.model.Users;

public interface UsersService {

	public Users registerUser(Users users);
	public List<Users> getAllUsers();
	public Users getUserByEmail(String email);
	public Map<String, Object> login(UserLoginRequest request);
}
