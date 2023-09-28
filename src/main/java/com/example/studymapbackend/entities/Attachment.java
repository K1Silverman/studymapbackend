package com.example.studymapbackend.entities;

import jakarta.persistence.*;
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
	private Integer postId;

	@NotNull
	@Column(name = "status", nullable = false)
	private String status;

}
