package com.exam.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.models.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {
    
}
