package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

import com.example.studymapbackend.entities.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer> {
	
	@Query("select f from Folder f where f.userId = ?1")
	List<Folder> getAllUserFolders(Integer userId);

	@Query("select f from Folder f where f.id = ?1 and f.userId = ?2")
	Optional<Folder> validateFolderOwner(Integer folderId, Integer userId);

}
