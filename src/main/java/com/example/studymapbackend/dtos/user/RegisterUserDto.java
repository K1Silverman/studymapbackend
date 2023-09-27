package com.example.studymapbackend.dtos.user;

import lombok.Data;

@Data
public class RegisterUserDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

}
