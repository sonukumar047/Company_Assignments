package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.modal.Users;
import com.app.repo.UserRepository;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optional = repository.findByEmail(username);
		if(optional.isEmpty()) {
			throw new UsernameNotFoundException("User not found with the Email: "+username);
		}
		else{
			Users user = optional.get();
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			return new User(user.getEmail(),user.getPassword(),grantedAuths);
		}
	}
}
