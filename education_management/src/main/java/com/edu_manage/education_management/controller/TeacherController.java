package com.edu_manage.education_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // Implement endpoints for teacher-related operations
    @GetMapping("/active-teachers")
    public List<Teacher> getActiveTeachers() {
        return teacherService.getActiveTeachers();
    }

}
