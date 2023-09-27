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
	@Mapping(source = "subject", target = "subject")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "position", target = "position")	
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(source = "chapterId", target = "chapterId")
	@Mapping(source = "status", target = "status")
	PostDto toDto(Post post);
	List<PostDto> toDtos(List<Post> posts);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "subject", target = "subject")
	@Mapping(source = "body", target = "body")
	@Mapping(source = "position", target = "position")
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(source = "chapterId", target = "chapterId")
	@Mapping(source = "status", target = "status")
	Post toEntity(PostDto post);
	
}
