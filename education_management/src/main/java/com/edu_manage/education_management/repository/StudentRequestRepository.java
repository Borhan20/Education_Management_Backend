package com.edu_manage.education_management.repository;

import com.edu_manage.education_management.entity.Role;
import com.edu_manage.education_management.entity.StudentRequest;
import com.edu_manage.education_management.entity.StudentRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRequestRepository extends JpaRepository<StudentRequest, Long> {


    List<StudentRequest> findByTeacherUserIdAndStatus(UUID userId, StudentRequestStatus studentRequestStatus);

    Optional<StudentRequest> findByStudentUserId(UUID studentId);
}
