package com.edu_manage.education_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Implement endpoints for teacher-related operations
    @GetMapping("/active-teachers")
    public List<Student> getActiveTeachers() {
        return studentService.getActiveStudents();
    }
}
