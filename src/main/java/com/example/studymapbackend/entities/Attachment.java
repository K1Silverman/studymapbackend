package com.example.studymapbackend.entities;

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
@Table(name = "attachments")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "body", nullable = false)
	private String body;
	
	@NotNull
	@Column(name = "post_id", nullable = false)
	private Post post;

	@NotNull
	@Column(name = "status", nullable = false)
	private String status;

}
