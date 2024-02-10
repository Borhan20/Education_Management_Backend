package com.edu_manage.education_management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getActiveTeachers() {
        return teacherRepository.findByUser_StatusTrue();
    }
}
