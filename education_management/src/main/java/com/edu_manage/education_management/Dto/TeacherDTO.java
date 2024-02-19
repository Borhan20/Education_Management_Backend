package com.edu_manage.education_management.Dto;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private UUID userId;
    private EMSUser emsUser;
    private String facultyName;
    private String designation;
    private String teacherId;


    public TeacherDTO(UUID userId, String facultyName, String designation, String teacherId,EMSUser emsUser) {
        this.userId = userId;
        this.emsUser = emsUser;
        this.teacherId = teacherId;
        this.facultyName = teacherId;
        this.designation = designation;
    }
}
