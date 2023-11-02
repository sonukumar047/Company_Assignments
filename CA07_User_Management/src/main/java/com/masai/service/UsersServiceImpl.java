package com.masai.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exception.UserNotFound;
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
	public Users registerUser(Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		users.setRole("ROLE"+users.getRole().toUpperCase());
		return usersRepository.save(users);
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Users getUserByEmail(String email) {
		return usersRepository.findByEmail(email)
				.orElseThrow(()-> 
				new UserNotFound("User not found by this email"));
	}

	@Override
	public Map<String, Object> login(UserLoginRequest request) {
		
		String email = request.getEmail();
//		String password = request.getPassword();
		
		Users users = usersRepository.findByEmail(email)
		.orElseThrow( ()->
		new UserNotFound("User Not Found"));
		
		if(passwordEncoder.matches(request.getPassword(), users.getPassword())) {
			String token = generateToken(users);
			
			Map<String, Object> responseBody = new HashMap<>();
			Map<String, Object> data = new HashMap<>();
			
			responseBody.put("status", 200);
			responseBody.put("message", "Logged in Successfully");
			responseBody.put("data", data);
			data.put("UserDetails", users);
			data.put("token", token);
			return responseBody;
		}
		
		
		
		return null;
	}
	
	public String generateToken(Users users) {
		Date now = new Date();
		Date valid = new Date(now.getTime()+30 * 24 * 3600 * 1000);
		
		String token = Jwts
				          .builder()
				          .setSubject(users.getEmail())
				          .claim("role", users.getRole())
				          .setIssuedAt(now)
				          .setExpiration(valid)
				          .signWith(secretKey)
				          .compact();
		
		return token;
	}

}
