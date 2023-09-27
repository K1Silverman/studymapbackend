package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class FolderDto {
	
	private Integer id;
	
	private String folderName;
	
	private Integer position;
	
	private String status;
	
	private Integer userId;
	
}
