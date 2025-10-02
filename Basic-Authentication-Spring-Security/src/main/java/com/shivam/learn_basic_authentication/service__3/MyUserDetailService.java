package com.shivam.learn_basic_authentication.service__3;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shivam.learn_basic_authentication.model__1.Users;
import com.shivam.learn_basic_authentication.repository__2.UserRepo;
@Service
public class MyUserDetailService implements UserDetailsService{
	//This is for Mapping Roles to User and implement loadByUsername

	@Autowired UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("--loadUserByUsername---Called------");

		Users user=userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("User Not Found"));	
		
		String role="ROLE_" +user.getRole().toUpperCase();
		return new User(
				
				user.getUsername(),
				user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(role))
				);
	}
	
}
