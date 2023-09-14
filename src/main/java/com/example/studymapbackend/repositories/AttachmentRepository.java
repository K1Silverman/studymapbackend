package com.example.studymapbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studymapbackend.entities.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

	
}
