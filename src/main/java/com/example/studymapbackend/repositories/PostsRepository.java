package com.example.studymapbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.Post;

public interface PostsRepository extends JpaRepository<Post, Integer>{
	@Query("select p from Post p where p.chapterId = ?1 and p.status = 'Active' order by p.position asc")
			List<Post>getAllChapterActivePosts(Integer chapterId);
}
