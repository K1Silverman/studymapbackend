package com.example.studymapbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
	
	@Query("select c from Chapter c where c.folderId = ?1 and c.status = 'Active' order by c.position asc")
	List<Chapter> getAllChaptersInFolder(Integer folderId);
}
