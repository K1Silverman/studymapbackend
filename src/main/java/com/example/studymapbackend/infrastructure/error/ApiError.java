package com.example.studymapbackend.infrastructure.error;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Data
public class ApiError {
	
	private HttpStatus errorCode;

	private String message;
	
}
