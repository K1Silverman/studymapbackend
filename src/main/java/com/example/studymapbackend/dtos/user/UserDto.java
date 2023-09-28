package com.example.studymapbackend.dtos.user;

import com.example.studymapbackend.entities.user.Role;
import lombok.Data;

@Data
public class UserDto {

	private Integer Id;
	
	private String firstName;
	
	private String lastName;
	
	private String pw;
	
	private String email;
	
	private Role role;

	private String status;
	
}
