package com.edu_manage.education_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.EMSUser;

public interface EMSUserRepository extends JpaRepository<EMSUser, Long> {
    // Custom queries if needed
}

