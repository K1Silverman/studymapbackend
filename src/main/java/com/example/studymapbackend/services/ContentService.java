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
		//TODO: Kui folderid puuduvad, siis saada exception teise response koodiga
		//		
		List<Folder> userFolders = folderRepository.getAllUserFolders(userId);
		System.out.println("userFolders: " + userFolders);
		List<FolderDto> userFoldersDtos = folderMapper.toDtos(userFolders);
		System.out.println("userFolderDTOS: " + userFoldersDtos);
		return userFoldersDtos;
	}

	public void addFolder(String folderName, Integer userId) {
		Folder newFolder = new Folder();
		newFolder.setFoldername(folderName);
		newFolder.setUser_id(userId);
		newFolder.setStatus("Active");
		folderRepository.save(newFolder);
	}

}
