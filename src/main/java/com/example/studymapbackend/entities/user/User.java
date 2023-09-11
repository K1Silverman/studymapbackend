package com.example.studymapbackend.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@NotNull
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	@NotNull
	@Column(name = "pw", nullable = false)
	private String pw;
	
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "status")
	private String status;
	
	
}
