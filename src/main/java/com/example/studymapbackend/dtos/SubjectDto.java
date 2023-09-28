package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class SubjectDto {

	private Integer id;
	
	private String name;
	
	private ThemeDto theme;
	
	private String status;
}
