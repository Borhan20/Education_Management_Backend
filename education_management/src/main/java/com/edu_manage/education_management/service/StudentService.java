package com.edu_manage.education_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.auth.AuthenticationRequest;
import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getActiveStudents() {
        return studentRepository.findByUser_StatusTrue();
    }

    public Object authenticate(AuthenticationRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }
}
