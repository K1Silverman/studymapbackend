package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class LoginResponse {
	
	private String firstName;
	
	private String lastName;
	
	private String eMail;
	
	private String role;
}
