package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.SubjectDto;
import com.example.studymapbackend.entities.Subject;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubjectMapper {
	SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "theme.id", target = "theme.id")
	@Mapping(source = "status", target = "status")
	SubjectDto toDto(Subject subject);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "theme.id", target = "theme.id")
	@Mapping(source = "status", target = "status")
	Subject toEntity (SubjectDto subject);
}