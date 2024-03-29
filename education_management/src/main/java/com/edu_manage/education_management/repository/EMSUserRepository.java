package com.edu_manage.education_management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Role;

public interface EMSUserRepository extends JpaRepository<EMSUser, UUID> {

    Optional<EMSUser> findByEmail(String string);
    // Custom queries if needed

    boolean existsByEmail(String email);


}

