package com.example.studymapbackend.entities;

import java.time.Instant;

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
@Table(name = "session")
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "hash", nullable = false)
	private String hash;
	
	@NotNull
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private String status;
	
	@NotNull
	@Column(name = "datetime_created", nullable = false)
	private Instant dateTimeCreated;
	
}
