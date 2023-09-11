package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.SessionDto;
import com.example.studymapbackend.entities.Session;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SessionMapper {
	
	SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "hash", target = "hash")
	@Mapping(source = "userId", target = "userId")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "dateTimeCreated", target = "dateTimeCreated")
	SessionDto toDto(Session session);
		
}
