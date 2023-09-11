package com.example.studymapbackend.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.studymapbackend.dtos.user.LoginRequestDto;
import com.example.studymapbackend.dtos.user.LoginResponseDto;
import com.example.studymapbackend.dtos.user.RegisterUserDto;
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
  
	@GetMapping("/user")
	@Operation(summary = "Gets user information", description = "Gets user information from db by user ID")
	public LoginResponseDto getUser(@RequestParam Integer userId) {
		return userService.getUserBy(userId);
	}
	
	@PostMapping("/user/login")
	@Operation(summary = "Login authetication", description = "Checks if there is an user with inserted e-mail address and password")
	public LoginResponseDto findUserAndAuthenticate(@RequestBody LoginRequestDto loginCredentials) {
		return userService.findUserAndAuthenticate(loginCredentials);
	}
	@GetMapping("/user/login")
	@Operation(summary = "Finds user of the session owner", description = "Looks up the session owner of the sessionHash")
	public LoginResponseDto findActiveSessionOwner(@RequestParam String sessionHash) {
		return userService.findActiveSessionOwner(sessionHash);
	}
	
	@PostMapping("/user")
	@Operation(summary = "Create user", description = "Creates user entry in DB")
	public void createUser(@RequestBody RegisterUserDto user) {
		userService.createUser(user);
	}
}