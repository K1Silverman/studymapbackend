package com.example.studymapbackend.repositories.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.studymapbackend.dtos.PostDto;
import com.example.studymapbackend.entities.Post;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostsMapper {

	PostsMapper INSTANCE = Mappers.getMapper(PostsMapper.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "position", target = "position")	
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(source = "subjectId", target = "subject.id")
	@Mapping(source = "ownerId", target = "owner.id")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "folderId", target = "folder.id")
	PostDto toDto(Post post);
	List<PostDto> toDtos(List<Post> posts);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "position", target = "position")	
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(source = "subject.id", target = "subjectId")
	@Mapping(source = "owner.id", target = "ownerId")
	@Mapping(source = "status", target = "status")
	@Mapping(source = "folder.id", target = "folderId")
	Post toEntity(PostDto post);
	
}
