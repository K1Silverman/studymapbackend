package com.example.studymapbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studymapbackend.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {
	@Query("select s from Session s where s.userId = ?1 AND s.status = 'Active'")
	Optional<Session> getUserActiveSession(Integer userId);

	@Query("select s from Session s where s.hash = ?1")
	Optional<Session> getSessionBy(String hash);
}
