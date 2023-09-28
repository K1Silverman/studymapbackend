package com.example.studymapbackend.repositories.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.ChapterDto;
import com.example.studymapbackend.entities.Chapter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ChapterMapper {
	ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "theme.id", target = "theme.id")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "folderId", target = "folderId")
	ChapterDto toDto(Chapter chapter);
	List<ChapterDto> toDtos(List<Chapter> chapters);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "theme.id", target = "theme.id")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "folderId", target = "folderId")
	Chapter toEntity (ChapterDto chapter);
}