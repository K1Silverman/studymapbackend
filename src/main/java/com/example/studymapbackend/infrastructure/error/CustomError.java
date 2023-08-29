package com.example.studymapbackend.infrastructure.error;

import lombok.Getter;

@Getter
public enum CustomError {
	INCORRECT_CREDENTIALS("E-mail address and Password do not match", "401"),
	USER_DOES_NOT_EXIST("There is no user with this e-mail address", "404");
	
	private String message;
	private String code;
	
	CustomError(String message, String code) {
		this.message = message;
		this.code = code;
	}
}
