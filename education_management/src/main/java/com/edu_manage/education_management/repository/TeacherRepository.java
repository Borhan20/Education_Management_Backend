package com.edu_manage.education_management.repository;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.edu_manage.education_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.edu_manage.education_management.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    List<Teacher> findByUser_StatusTrue();
    Optional<Teacher> findByTeacherId(String string);

    void deleteByUserId(UUID userId);

}
