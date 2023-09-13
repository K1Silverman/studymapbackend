package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studymapbackend.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}
