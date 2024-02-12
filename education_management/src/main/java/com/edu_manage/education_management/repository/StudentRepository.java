package com.edu_manage.education_management.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUser_StatusTrue();

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean findByName(String username);
    

    
}
