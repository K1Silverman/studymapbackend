package com.example.studymapbackend.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class PostDto {
	
	private Integer id;

	private String subject;
	
	private String body;
	
	private Integer position;
	
	private Instant timestamp;
	
	private Integer chapterId;
	
	private String status;

}
