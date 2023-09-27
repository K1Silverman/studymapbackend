package com.example.studymapbackend.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ChapterDto {

	private Integer id;
	
	private String name;
	
	private ThemeDto theme;

	private Integer position;
	
	private String status;
	
	private Integer folderId;
	
	private List<PostDto> posts;
}
