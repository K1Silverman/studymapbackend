package com.example.studymapbackend.entities;

import java.time.Instant;

import com.example.studymapbackend.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "body", nullable = false)
	private String body;
	
	@NotNull
	@Column(name = "position", nullable = false)
	private Integer position;
	
	@NotNull
	@Column(name = "timestamp", nullable = false)
	private Instant timestamp;
	
	@NotNull
	@Column(name = "subject_id", nullable = false)
	private Subject subject;
	
	@NotNull
	@Column(name = "owner_id", nullable = false)
	private User owner;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private String status;
	
	@NotNull
	@Column(name = "folder_id", nullable = false)
	private Folder folder;
		
}
