package com.shivam.learn_basic_authentication.service__3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shivam.learn_basic_authentication.model__1.Users;
import com.shivam.learn_basic_authentication.repository__2.UserRepo;
@Service
public class UsersService {
	@Autowired UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Users registerUser(Users user) {
		//1.Check if user already exist then throw exc
		
		if(userRepo.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("User Already Exists");
			
		}
		
		//2. If role not provided then set default
		if(user.getRole()==null ||user.getRole().isBlank()) {
			user.setRole("admin");
		}
		
		//3.Save Password in Encoded Form
	    user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepo.save(user);
	}
}
