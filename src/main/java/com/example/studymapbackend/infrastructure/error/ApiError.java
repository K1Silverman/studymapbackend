package com.example.studymapbackend.infrastructure.error;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
	
	private HttpStatus errorCode;

	private String message;
	
}
