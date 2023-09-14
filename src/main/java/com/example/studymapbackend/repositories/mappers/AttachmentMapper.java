package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.studymapbackend.dtos.AttachmentDto;
import com.example.studymapbackend.entities.Attachment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttachmentMapper {
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "post.id", target = "postId")
	@Mapping(source = "status", target = "status")
	AttachmentDto toDto(Attachment attachment);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "postId", target = "post.id")
	@Mapping(source = "status", target = "status")
	Attachment toEntity(AttachmentDto attachment);
}
