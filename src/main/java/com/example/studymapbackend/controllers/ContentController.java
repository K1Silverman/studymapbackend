package com.example.studymapbackend.controllers;

import com.example.studymapbackend.dtos.AttachmentDto;
import com.example.studymapbackend.dtos.ChapterDto;
import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.dtos.PostDto;
import com.example.studymapbackend.services.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Adds new folder to user", description = "Creates a new row in db for users' folders")
    public List<FolderDto> addFolder(@RequestBody FolderDto newFolderDto) {
        return contentService.addFolder(newFolderDto);
    }

    @GetMapping("/content/folder/chapter")
    @Operation(summary = "Gets all chapter inside folder", description = "Searches all chapters that have associated folder id. Also returns posts inside the chapter")
    public List<ChapterDto> getAllChaptersInFolder(@RequestParam Integer userId, @RequestParam Integer folderId) throws IllegalAccessException {
        if (contentService.validateFolderOwner(userId, folderId)) {
            return contentService.getAllChaptersInFolder(folderId);
        } else {
            throw new IllegalAccessException();
        }
    }

    @PostMapping("/content/folder/chapter")
    @Operation(summary = "Add new chapter to folder", description = "Creates new chapter in database. Returns all chapters inside folder")
    public List<ChapterDto> addChapter(@RequestBody ChapterDto newChapterDto) {
        return contentService.saveChapter(newChapterDto);
    }
    @PostMapping("/content/folder/chapter/post")
    @Operation(summary = "Saves post to folder", description = "Saves post to folder with theme and subject.")
    public PostDto savePost(@RequestBody PostDto post, @RequestBody(required = false) List<AttachmentDto> attachments) {

        return contentService.savePost(post, attachments);
    }
}
