package com.example.studymapbackend.infrastructure.exception;

import org.springframework.http.HttpStatus;

import com.example.studymapbackend.infrastructure.error.CustomError;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
@Data
public class AuthenticationFailedException extends RuntimeException {
	
	private HttpStatus code;
	
	private String message;

//		
	public AuthenticationFailedException(CustomError error) {
	
		super(error.getMessage());
		this.message = error.getMessage();
		this.code = error.getCode();
	}
	
}
