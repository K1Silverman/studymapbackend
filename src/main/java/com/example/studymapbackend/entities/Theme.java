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
@Table(name = "themes")
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "first_color", nullable = false)
	private String firstColor;
	
	@NotNull
	@Column(name = "secondary_color", nullable = false)
	private String secondaryColor;
	
	@NotNull
	@Column(name = "buttons_color", nullable = false)
	private String buttonsColor;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private String status;
	

}
