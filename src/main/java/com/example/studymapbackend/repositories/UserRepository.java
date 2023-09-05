package com.example.studymapbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.email = ?1")
	Optional<User> findUserBy(String email);
	
}
