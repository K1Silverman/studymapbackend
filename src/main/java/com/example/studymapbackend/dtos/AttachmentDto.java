package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class AttachmentDto {

	private Integer id;
	
	private String body;
	
	private Integer postId;
	
	private String status;
	
}
