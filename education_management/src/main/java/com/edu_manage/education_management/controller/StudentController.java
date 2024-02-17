package com.edu_manage.education_management.controller;

import java.util.List;
import java.util.UUID;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.repository.TeacherRepository;
import com.edu_manage.education_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.edu_manage.education_management.auth.AuthenticationRequest;
//import com.edu_manage.education_management.auth.AuthenticationRequest;
//import com.edu_manage.education_management.auth.AuthenticationResponse;
import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.Teacher;
//import com.edu_manage.education_management.service.JwtService;
import com.edu_manage.education_management.service.StudentService;
import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/test")
    @ResponseBody
    public String getTest(){
        return "tested successfully";
    }

    // Implement endpoints for teacher-related operations
    @GetMapping("/active-teachers")
    public List<Teacher> getActiveTeachers() {
        return teacherService.getActiveTeachers();
    }


    //the below method represent student can view his profile
    @GetMapping("/{userId}/profile")
    public ResponseEntity<Student> viewProfile(@PathVariable UUID userId) {
        Student student = studentService.getStudentById(userId);
        return ResponseEntity.ok(student);
    }

    //the below method shows student can edit his information
    @PutMapping("/{userId}/profile")
    public ResponseEntity<String> editProfile(@PathVariable UUID userId, @RequestBody EMSUser updatedStudent) {
        studentService.editProfile(userId, updatedStudent);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @PostMapping("/{userId}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable UUID userId, @RequestParam String newPassword) {
        studentService.resetPassword(userId, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/{userId}/send-advisor-request")
    public ResponseEntity<String> sendAdvisorRequest(@PathVariable UUID userId, @RequestBody Teacher teacher) {
        studentService.sendAdvisorRequest(userId, teacher);
        return ResponseEntity.ok("Advisor request sent successfully");
    }

}
