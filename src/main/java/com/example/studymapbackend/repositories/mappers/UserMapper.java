package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.user.LoginRequestDto;
import com.example.studymapbackend.dtos.user.UserDto;
import com.example.studymapbackend.entities.user.User;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(source = "firstname", target = "firstName")
	@Mapping(source = "lastname", target = "lastName")
	@Mapping(source = "pw", target = "pw")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "role", target = "role")
	@Mapping(source = "status", target = "status")
	UserDto toDto(User user);
	
	@Mapping(source = "firstName", target = "firstname")
	@Mapping(source = "lastName", target = "lastname")
	@Mapping(source = "pw", target = "pw")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "role", target = "role")
	@Mapping(source = "status", target = "status")
	User toEntity(UserDto user);
	User toEntity(LoginRequestDto user);
	
}
