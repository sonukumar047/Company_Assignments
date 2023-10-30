package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.UsersRegistrationException;
import com.app.modal.Users;
import com.app.repo.UserRepository;

@Service
public class UserService {

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public Users registerUsers(Users user) {

	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        return user;
	    }
	
}
