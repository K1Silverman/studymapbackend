package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studymapbackend.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
}
