package com.example.studymapbackend.repositories.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.FolderDto;
import com.example.studymapbackend.entities.Folder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FolderMapper {
	
	FolderMapper INSTANCE = Mappers.getMapper(FolderMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "foldername", target = "folderName")
	@Mapping(source = "position", target = "position")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "user_id", target = "userId")
	FolderDto toDto(Folder folder);
	List<FolderDto> toDtos(List<Folder> folders);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "folderName", target = "foldername")
	@Mapping(source = "position", target = "position")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "userId", target = "user_id")
	Folder toEntity(FolderDto folder);
}
