package com.example.studymapbackend.infrastructure.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Data
public class ApiError {

	private String message;
	
	private String errorCode;
	
}
