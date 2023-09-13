package com.example.studymapbackend.dtos;

import java.util.List;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class PostDto {
	
	private Integer id;
	
	private String body;
	
	private Integer position;
	
	private String timestamp;
	
	private SubjectDto subject;
	
	private List<AttachmentDto> attachments;
	
	private Integer ownerId;
	
	private String status;
	
	private Integer folderId;
}
