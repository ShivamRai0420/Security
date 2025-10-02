package com.shivam.learn_basic_authentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shivam.learn_basic_authentication.BasicAuthenticationSpringSecurityApplication;
import com.shivam.learn_basic_authentication.model__1.Users;
import com.shivam.learn_basic_authentication.repository__2.UserRepo;
import com.shivam.learn_basic_authentication.service__3.UsersService;

@RestController
@RequestMapping("/api")
public class HelloController {
	@Autowired UserRepo userRepo;


    private final BasicAuthenticationSpringSecurityApplication basicAuthenticationSpringSecurityApplication;
	@Autowired UsersService usersService;

    HelloController(BasicAuthenticationSpringSecurityApplication basicAuthenticationSpringSecurityApplication) {
        this.basicAuthenticationSpringSecurityApplication = basicAuthenticationSpringSecurityApplication;
    }
	
	@GetMapping("/hello")
	public String hellos() {
		return "Hello Auth";
	}
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		System.out.println(user);
		return usersService.registerUser(user);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/getAll")
	public List<Users> getAllUsers() {
    	System.out.println("--getAllUsers---Called------");

		return userRepo.findAll();
	}

}
