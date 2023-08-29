package com.example.studymapbackend.dtos;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class RegisterUserDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

}
