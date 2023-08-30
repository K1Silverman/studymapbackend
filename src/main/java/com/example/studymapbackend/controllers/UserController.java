package com.example.studymapbackend.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.studymapbackend.dtos.LoginResponse;
import com.example.studymapbackend.dtos.RegisterUserDto;
import com.example.studymapbackend.dtos.UserDto;
import com.example.studymapbackend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class UserController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("/user/email")
	@Operation(summary = "Checks e-mail address", description = "Check if e-mail address already exists in db.")
	public Boolean checkEmailExists(@RequestParam String email) {
		return userService.checkEmailAddressExists(email);
	}
  
	@GetMapping("/user/login")
	@Operation(summary = "Login authetication", description = "Checks if there is an user with inserted e-mail address and password")
	public LoginResponse findUserAndAuthenticate(@RequestParam UserDto user) {
		return userService.findUserAndAuthenticate(user);
	}
  
	@PostMapping("/user")
	@Operation(summary = "Create user", description = "Creates user entry in DB")
	public void createUser(@RequestBody RegisterUserDto user) {
		userService.createUser(user);
	}
}