package com.edu_manage.education_management.Dto;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private UUID userId;
    private EMSUser emsUser;
    private String department;
    private String batchNo;
    private String studentId;
    private Teacher advisor;
}
