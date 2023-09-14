package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	@Query("select t from Themes t where t.status = 'Default'")
	Theme getDefaultTheme();
}
