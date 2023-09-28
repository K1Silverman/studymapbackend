package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.ThemeDto;
import com.example.studymapbackend.entities.Theme;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ThemeMapper {
	
	ThemeMapper INSTANCE = Mappers.getMapper(ThemeMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "firstColor", target = "firstColor")
	@Mapping(source = "secondaryColor", target = "secondaryColor")
	@Mapping(source = "buttonsColor", target = "buttonsColor")
	@Mapping(source = "status", target = "status")
	ThemeDto toDto(Theme theme);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "firstColor", target = "firstColor")
	@Mapping(source = "secondaryColor", target = "secondaryColor")
	@Mapping(source = "buttonsColor", target = "buttonsColor")
	@Mapping(source = "status", target = "status")
	Theme toEntity(ThemeDto theme);

}
