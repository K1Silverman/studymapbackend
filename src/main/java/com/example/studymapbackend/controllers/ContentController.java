package com.example.studymapbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.services.ContentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class ContentController {
	
	@Resource
	private ContentService contentService;
	
	@GetMapping("/content/folder")
	@Operation(summary = "Gets all user's folders", description = "Fetches all folders from database that are connected to user's id.")
	public List<FolderDto> getAllUserFolders(Integer userId) {
		return contentService.getAllUserFolders(userId);
	}
	
	@PostMapping("/content/folder")
	@Operation(summary = "Adds new folder to user", description = "Creates a new row in db for users's folders")
	public void addFolder(String folderName, Integer userId) {
		contentService.addFolder(folderName, userId);
	}
}
