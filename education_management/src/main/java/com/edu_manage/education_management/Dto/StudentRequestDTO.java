package com.edu_manage.education_management.Dto;

import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.StudentRequestStatus;
import com.edu_manage.education_management.entity.Teacher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    private Long requestId;
    private String studentName;
    private String teacherName;
    @Enumerated(EnumType.STRING)
    private StudentRequestStatus status;

}
