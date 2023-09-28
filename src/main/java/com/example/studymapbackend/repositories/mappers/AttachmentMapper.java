package com.example.studymapbackend.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.studymapbackend.dtos.AttachmentDto;
import com.example.studymapbackend.entities.Attachment;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AttachmentMapper {
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "postId", target = "postId")
	@Mapping(source = "status", target = "status")
	AttachmentDto toDto(Attachment attachment);
	List<AttachmentDto> toDtos(List<Attachment> attachments);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "postId", target = "postId")
	@Mapping(source = "status", target = "status")
	Attachment toEntity(AttachmentDto attachment);
	List<Attachment> toEntities(List<AttachmentDto> attachmentDtos);
}
