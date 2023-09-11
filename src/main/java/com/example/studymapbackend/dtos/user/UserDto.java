package com.example.studymapbackend.dtos.user;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class UserDto {
	
	private String firstName;
	
	private String lastName;
	
	private String pw;
	
	private String email;
	
	private String role;

	private String status;
	
}
