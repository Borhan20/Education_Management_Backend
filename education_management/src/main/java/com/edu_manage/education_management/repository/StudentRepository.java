package com.edu_manage.education_management.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.edu_manage.education_management.entity.EMSUser;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByUser_StatusTrue();


    Optional<Student> findByStudentId(String string);
}
