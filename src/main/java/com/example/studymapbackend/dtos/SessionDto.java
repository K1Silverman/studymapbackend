package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class SessionDto {
	
	private Integer id;
	
	private String hash;
	
	private Integer userId;
	
	private String status;
	
	private String dateTimeCreated;
}
