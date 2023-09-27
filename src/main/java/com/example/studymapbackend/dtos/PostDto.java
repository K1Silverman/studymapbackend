package com.example.studymapbackend.dtos;

import lombok.Data;

@Data
public class PostDto {
	
	private Integer id;

	private String subject;
	
	private String body;
	
	private Integer position;
	
	private String timestamp;
	
	private Integer chapterId;
	
	private String status;

}
