package com.example.studymapbackend.infrastructure.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum CustomError {
	INCORRECT_CREDENTIALS(HttpStatus.UNAUTHORIZED, "E-mail address and Password do not match"),
	USER_DOES_NOT_EXIST(HttpStatus.NOT_FOUND, "There is no user with this e-mail address");
	
	private HttpStatus code;
	
	private String message;
	
	CustomError(HttpStatus code, String message) {
		this.message = message;
		this.code = code;
	}
}
