package com.example.studymapbackend.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.studymapbackend.infrastructure.error.ApiError;
import com.example.studymapbackend.infrastructure.exception.AuthenticationFailedException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ApiError> handleDataNotFoundException(AuthenticationFailedException exception) {
		
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setErrorCode(exception.getCode());
		
		return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
	}
}
