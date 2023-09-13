package com.example.studymapbackend.dtos;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class SubjectDto {

	private Integer id;
	
	private String name;
	
	private ThemeDto theme;
	
	private String status;
}
