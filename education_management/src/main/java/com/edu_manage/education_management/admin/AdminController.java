package com.edu_manage.education_management.admin;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.Teacher;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return adminService.getAllTeachers();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    //creating new student by admin by taking form parameters
    @PostMapping("/create-students")
    public ResponseEntity<String> createStudents(@RequestParam String email,@RequestParam String phone,
                                                 @RequestParam String name,@RequestParam String password, @RequestParam String department,
                                                 @RequestParam String studentId,@RequestParam String batchNo){

        adminService.createStudents(UUID.randomUUID(),email,phone,
                name,password,department,studentId,batchNo);
        return ResponseEntity.ok("Student Created  successfully");
    }

    @PostMapping("/create-teachers")
    public ResponseEntity<String> createTeachers(@RequestParam String email,@RequestParam String phone,
                                                 @RequestParam String name,@RequestParam String password, @RequestParam String faculty,
                                                 @RequestParam String designation,@RequestParam String teacherId){

        adminService.createTeachers(UUID.randomUUID(), email,phone,
                name,password, faculty,
                designation,teacherId);
        return ResponseEntity.ok("Student Created  successfully");
    }

    @PostMapping("/add-role/{userId}")
    public ResponseEntity<String> addRoleToUser(@PathVariable UUID userId, @RequestParam String roleName) {
        adminService.addRoleToUser(userId, roleName);
        return ResponseEntity.ok("Role added successfully");
    }

    @PostMapping("/deactivate-user/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable UUID userId) {
        adminService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated successfully");
    }
}

