package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.example.studymapbackend.entities.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer> {
	
	@Query("select f from Folder f where f.user_id = ?1")
	List<Folder> getAllUserFolders(Integer userId);

}
