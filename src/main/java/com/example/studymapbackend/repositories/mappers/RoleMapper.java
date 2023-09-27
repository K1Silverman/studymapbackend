package com.example.studymapbackend.repositories.mappers;

import com.example.studymapbackend.dtos.user.RoleDto;
import com.example.studymapbackend.entities.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "permissions", target = "permissions")
    RoleDto toDto(Role role);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "permissions", target = "permissions")
    Role toEntity(RoleDto role);
}
