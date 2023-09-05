package com.example.studymapbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "folders")
public class Folder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "foldername", nullable = false)
	private String foldername;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private String status;
	
	@NotNull
	@Column(name = "user_id", nullable = false)
	private Integer user_id;
	
}
