package com.edu_manage.education_management.service;

import java.util.List;
import java.util.UUID;

import com.edu_manage.education_management.entity.*;
import com.edu_manage.education_management.repository.EMSUserRepository;
import com.edu_manage.education_management.repository.StudentRequestRepository;
import com.edu_manage.education_management.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.auth.AuthenticationRequest;
import com.edu_manage.education_management.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EMSUserRepository emsUserRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRequestRepository studentRequestRepository;

    public List<Student> getActiveStudents() {
        return studentRepository.findByUser_StatusTrue();
    }

    public Student getStudentById(UUID userId) {
        return studentRepository.findById(userId).orElse(null);
    }

    public void editProfile(UUID userId, EMSUser updatedStudent) {
        EMSUser existingStudent = emsUserRepository.findById(userId).orElse(null);
        if (existingStudent != null) {
            // Assuming you have a copyProperties method to copy relevant properties
            BeanUtils.copyProperties(updatedStudent, existingStudent, "userId");
            emsUserRepository.save(existingStudent);
        }
    }

    public void resetPassword(UUID userId, String newPassword) {
        EMSUser student = emsUserRepository.findById(userId).orElse(null);
        if (student != null) {
            // Assuming you have a setPassword method in your Student entity
            student.setPassword(newPassword);
            emsUserRepository.save(student);
        }
    }


    public void sendAdvisorRequest(UUID userId, UUID teacherId) {
        Student student = studentRepository.findById(userId).orElse(null);
        Teacher advisor = teacherRepository.findById(teacherId).orElse(null);


        if (student != null && advisor != null) {
            StudentRequest studentRequest = new StudentRequest();
            studentRequest.setStudent(student);
            studentRequest.setTeacher(advisor);
            studentRequest.setStatus(StudentRequestStatus.PENDING);
            studentRequestRepository.save(studentRequest);
            student.setAdvisor(advisor);
            studentRepository.save(student);
        }
    }
}
