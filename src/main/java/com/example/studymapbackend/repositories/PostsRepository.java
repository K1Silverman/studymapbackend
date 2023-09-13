package com.example.studymapbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.Post;

public interface PostsRepository extends JpaRepository<Post, Integer>{
	@Query("select p from Post p where p.ownerId = ?1 and p.folderId = ?2")
	List<Post> getAllFolderActivePosts(Integer userId, Integer folderId);
}
