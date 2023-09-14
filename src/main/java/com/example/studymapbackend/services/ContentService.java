package com.example.studymapbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studymapbackend.dtos.AttachmentDto;
import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.dtos.PostDto;
import com.example.studymapbackend.dtos.SubjectDto;
import com.example.studymapbackend.dtos.ThemeDto;
import com.example.studymapbackend.entities.Attachment;
import com.example.studymapbackend.entities.Folder;
import com.example.studymapbackend.entities.Post;
import com.example.studymapbackend.entities.Subject;
import com.example.studymapbackend.entities.Theme;
import com.example.studymapbackend.repositories.AttachmentRepository;
import com.example.studymapbackend.repositories.FolderRepository;
import com.example.studymapbackend.repositories.PostsRepository;
import com.example.studymapbackend.repositories.SubjectRepository;
import com.example.studymapbackend.repositories.ThemeRepository;
import com.example.studymapbackend.repositories.mappers.AttachmentMapper;
import com.example.studymapbackend.repositories.mappers.FolderMapper;
import com.example.studymapbackend.repositories.mappers.PostsMapper;
import com.example.studymapbackend.repositories.mappers.SubjectMapper;
import com.example.studymapbackend.repositories.mappers.ThemeMapper;

import jakarta.annotation.Resource;

@Service
public class ContentService {

	@Resource
	private FolderRepository folderRepository;

	@Resource
	private FolderMapper folderMapper;
	
	@Resource
	private PostsRepository postsRepository;
	
	@Resource
	private PostsMapper postsMapper;
	
	@Resource
	private SubjectRepository subjectRepository;
	
	@Resource
	private SubjectMapper subjectMapper;

	@Resource
	private ThemeRepository themeRepository;
	
	@Resource
	private ThemeMapper themeMapper;
	
	@Resource
	private AttachmentRepository attachmentRepository;
	
	@Resource
	private AttachmentMapper attachmentMapper;
	
	public List<FolderDto> getAllUserFolders(Integer userId) {
		List<Folder> userFolders = folderRepository.getAllUserFolders(userId);
		List<FolderDto> userFoldersDtos = folderMapper.toDtos(userFolders);
		return userFoldersDtos;
	}

	public List<FolderDto> addFolder(FolderDto newFolderDto) {
		Folder newFolder = new Folder();
		newFolder.setFoldername(newFolderDto.getFolderName());
		newFolder.setPosition(getLastPosition(newFolderDto.getUserId()));
		newFolder.setUser_id(newFolderDto.getUserId());
		newFolder.setStatus("Active");
		System.out.println("New Folder: " + newFolder);
		folderRepository.save(newFolder);
		List<FolderDto> allFolders = folderMapper.toDtos(folderRepository.getAllUserFolders(newFolderDto.getUserId()));
		return allFolders;
	}
	
	private Integer getLastPosition(Integer userId) {
		List<Folder> userFolders = folderRepository.getAllUserFolders(userId);
		Integer lastPosition = 0;
		for (Folder folder: userFolders) {
			if (folder.getPosition() > lastPosition) {
				lastPosition = folder.getPosition();
			}
		}
		return lastPosition;
	}

	public void createDefaultFolder(Integer userId) {
		Folder newFolder = new Folder();
		newFolder.setFoldername("Unfoldered subjects");
		newFolder.setPosition(0);
		newFolder.setUser_id(userId);
		newFolder.setStatus("Default");
		folderRepository.save(newFolder);
	}

	public List<PostDto> getAllPostsInFolder(Integer userId, Integer folderId) {
		List<Post> activeFolderPosts = postsRepository.getAllFolderActivePosts(userId, folderId);
		List<PostDto> activeFolderPostsDtos = postsMapper.toDtos(activeFolderPosts);
		for (PostDto post : activeFolderPostsDtos) {
			Integer subjectId = post.getSubject().getId();
			SubjectDto subject = subjectMapper.toDto(subjectRepository.findById(subjectId).get());
			Integer themeId = subject.getTheme().getId();
			ThemeDto theme = themeMapper.toDto(themeRepository.getReferenceById(themeId));
			subject.setTheme(theme);
			post.setSubject(subject);
		}
		return activeFolderPostsDtos;
	}

	public void savePost(PostDto post) {
		
		Post postEntity = new Post();
		SubjectDto subjectDto = post.getSubject();
		Subject subjectEntity = subjectMapper.toEntity(subjectDto);
		ThemeDto themeDto = subjectDto.getTheme();


		if (themeDto.getStatus().equals("Default")) {
			subjectEntity.setTheme(themeRepository.getDefaultTheme());
		} else if (themeDto.getStatus().equals("Active")) {
			subjectEntity.setTheme(themeRepository.getReferenceById(themeDto.getId()));
		} else {
			subjectEntity.setTheme(themeRepository.save(themeMapper.toEntity(themeDto)));
		}
		
		
		List<AttachmentDto> attachmentsDtos = post.getAttachments();
		List<Attachment> attachments = new ArrayList<Attachment>();
		
		for(AttachmentDto attachmentDto : attachmentsDtos) {
			if (attachmentDto.getId() != null) {
				attachments.add(attachmentMapper.toEntity(attachmentDto));
			}
		}
	}

}
