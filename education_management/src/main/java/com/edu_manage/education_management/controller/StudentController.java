package com.edu_manage.education_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // Implement endpoints for teacher-related operations
    @GetMapping("/active-teachers")
    public List<Student> getActiveTeachers() {
        return studentService.getActiveStudents();
    }
    

@PostMapping("/authenticate")
    public ResponseEntity<Object> register(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(studentService.authenticate(request));
    }
}
