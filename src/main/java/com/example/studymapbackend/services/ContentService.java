package com.example.studymapbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.entities.Folder;
import com.example.studymapbackend.repositories.FolderRepository;
import com.example.studymapbackend.repositories.mappers.FolderMapper;

import jakarta.annotation.Resource;

@Service
public class ContentService {

	@Resource
	private FolderRepository folderRepository;

	@Resource
	private FolderMapper folderMapper;

	public ContentService(FolderRepository folderRepository, FolderMapper folderMapper) {
		this.folderRepository = folderRepository;
		this.folderMapper = folderMapper;
	}

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

}
