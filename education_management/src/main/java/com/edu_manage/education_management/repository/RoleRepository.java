package com.edu_manage.education_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String roleName);
    
}
