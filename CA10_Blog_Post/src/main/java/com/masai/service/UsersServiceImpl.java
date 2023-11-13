package com.masai.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.UserNotFoundException;
import com.masai.model.UserLoginRequest;
import com.masai.model.Users;
import com.masai.repository.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UsersServiceImpl implements UsersService{
	
	private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Users registerUser(Users user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("ROLE"+user.getRole().toUpperCase());
			return usersRepository.save(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Failed to register User! Try again.");
		}
	}

	@Override
	public Users getUserByEmail(String email) {
		Optional<Users> userByEmail = usersRepository.findByEmail(email);
		if(userByEmail.isPresent()) {
			return userByEmail.get();
		}
		else throw new UsernameNotFoundException("User not found with email:"+email);
	}

	@Override
	public List<Users> getAllUsers() {
		try {
			return usersRepository.findAll();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Exception occured while retriving users");
		}
	}

	@Override
	public Map<String, Object> login(UserLoginRequest request) {
		String email = request.getEmail();
		Users user = usersRepository.findByEmail(email).orElseThrow(()-> 
		new UserNotFoundException("User not found with email:"+email));
		
		if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			String token = generateToken(user);
			
			Map<String, Object> responseBody = new HashMap<>();
			Map<String, Object> data = new HashMap<>();
			
			responseBody.put("status", 200);
			responseBody.put("message", "Logged in successfully!");
			responseBody.put("data", data);
			
			data.put("UserDetails", user);
			data.put("token", token);
			return responseBody;
		}
		return null;
	}

	@Override
	public String deleteUserById(int userId) {
		Optional<Users> userById = usersRepository.findById(userId);
		if(userById.isPresent()) {
			usersRepository.deleteById(userId);
			return "User deleted successfully";
		}
		else throw new UsernameNotFoundException("User not found with userId:"+userId);
		
	}
	
	
	
	public String generateToken(Users user) {
		Date now = new Date();
		Date valid = new Date(now.getTime()+30 * 24 * 3600 * 1000);
		
		String token = Jwts
				          .builder()
				          .setSubject(user.getEmail())
				          .claim("role", user.getRole())
				          .setIssuedAt(now)
				          .setExpiration(valid)
				          .signWith(secretKey)
				          .compact();
		return token;
	}

}
