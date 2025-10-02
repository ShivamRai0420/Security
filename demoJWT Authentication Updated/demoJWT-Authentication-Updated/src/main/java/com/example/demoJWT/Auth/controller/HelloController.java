package com.example.demoJWT.Auth.controller;

import com.example.demoJWT.Auth.model.Users;
import com.example.demoJWT.Auth.repository.UserRepo;
import com.example.demoJWT.Auth.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UsersService usersService;


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
