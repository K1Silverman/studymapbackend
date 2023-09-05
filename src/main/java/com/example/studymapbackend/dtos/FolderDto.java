package com.example.studymapbackend.dtos;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class FolderDto {
	
	private Integer id;
	
	private String folderName;
	
	private String status;
	
	private Integer userId;
	
}
