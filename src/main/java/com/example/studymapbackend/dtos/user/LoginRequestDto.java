package com.example.studymapbackend.dtos.user;

import lombok.Data;

@Data
public class LoginRequestDto {
	
	private String email;
	
	private String password;
}
