package com.example.studymapbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.dtos.PostDto;
import com.example.studymapbackend.services.ContentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class ContentController {
	
	@Resource
	private ContentService contentService;
	
	@GetMapping("/content/folder")
	@Operation(summary = "Gets all user's folders", description = "Fetches all folders from database that are connected to user's id.")
	public List<FolderDto> getAllUserFolders(@RequestParam Integer userId) {
		return contentService.getAllUserFolders(userId);
	}
	
	@PostMapping("/content/folder")
	@Operation(summary = "Adds new folder to user", description = "Creates a new row in db for users's folders")
	public List<FolderDto> addFolder(@RequestBody FolderDto newFolderDto) {
		return contentService.addFolder(newFolderDto);
	}
	
	@GetMapping("/content/folder/post")
	@Operation(summary = "Gets all posts inside folder", description = "Searches all posts that have associated folder id.")
	public List<PostDto> getAllPostsInFolder(@RequestParam Integer userId, @RequestParam Integer folderId) {
		return contentService.getAllPostsInFolder(userId, folderId);		
	}
	
	@PostMapping("/content/folder/post")
	@Operation(summary = "Saves post to folder", description = "Saves post to folder with theme and subject.")
	public PostDto savePost(@RequestBody PostDto post) {
		contentService.savePost(post);
		return null;
	}
}
