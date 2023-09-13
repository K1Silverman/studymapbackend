package com.example.studymapbackend.dtos;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class ThemeDto {
	
	private Integer id;
	
	private String name;
	
	private String firstColor;
	
	private String secondaryColor;
	
	private String buttonsColor;
	
	private String status;
}