package com.example.studymapbackend.dtos.user;

import lombok.Data;

@Data
public class LoginResponseDto {
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String eMail;
	
	private String role;
	
	private String status;
	
	private String sessionHash;
}
