package com.edu_manage.education_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
