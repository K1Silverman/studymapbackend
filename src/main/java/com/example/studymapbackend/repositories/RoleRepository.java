package com.example.studymapbackend.repositories;

import com.example.studymapbackend.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.name = ?1")
    Role getRoleBy(String name);
}
