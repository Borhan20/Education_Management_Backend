package com.edu_manage.education_management.service;


import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.StudentRequestStatus;
import com.edu_manage.education_management.repository.EMSUserRepository;
import com.edu_manage.education_management.repository.StudentRepository;
import com.edu_manage.education_management.repository.StudentRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.repository.TeacherRepository;
import com.edu_manage.education_management.entity.StudentRequest;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EMSUserRepository emsUserRepository;

    @Autowired
    private StudentRequestRepository studentRequestRepository;

    public List<Teacher> getActiveTeachers() {

        return teacherRepository.findByUser_StatusTrue();
    }

    public Teacher getTeacherById(UUID userId) {
        return teacherRepository.findById(userId).orElse(null);
    }


    public void editProfile(UUID userId, Teacher updatedTeacher) {
        Teacher existingTeacher = teacherRepository.findById(userId).orElse(null);
        if (existingTeacher != null) {
            // Assuming you have a copyProperties method to copy relevant properties
            BeanUtils.copyProperties(updatedTeacher, existingTeacher, "userId");
            teacherRepository.save(existingTeacher);
        }
    }

    public void resetPassword(UUID userId, String newPassword) {
        EMSUser teacher = emsUserRepository.findById(userId).orElse(null);
        if (teacher != null) {
            // Assuming you have a setPassword method in your Teacher entity
            teacher.setPassword(newPassword);
            emsUserRepository.save(teacher);
        }
    }


    public List<StudentRequest> getStudentRequests(UUID userId) {
        return studentRequestRepository.findByTeacherUserIdAndStatus(userId, StudentRequestStatus.PENDING);
    }


    public void acceptStudentRequest(UUID userId, UUID studentId) {
        StudentRequest new_request = studentRequestRepository.findByStudentUserId(studentId).orElse(null);
        if (new_request != null && new_request.getTeacher().getUserId().equals(userId) && new_request.getStatus() == StudentRequestStatus.PENDING) {
            new_request.setStatus(StudentRequestStatus.ACCEPTED);
            studentRequestRepository.save(new_request);
        }
    }


    public void dismissStudentRequest(UUID userId, UUID studentId) {
        StudentRequest new_request = studentRequestRepository.findByStudentUserId(studentId).orElse(null);
        if (new_request != null && new_request.getTeacher().getUserId().equals(userId) && new_request.getStatus() == StudentRequestStatus.PENDING) {
            new_request.setStatus(StudentRequestStatus.DISMISSED);
            studentRequestRepository.save(new_request);
        }
    }


    public List<Student> getAdvisorStudents(UUID userId) {
        return studentRepository.findByAdvisorUserId(userId);
    }

    public void removeStudentFromAdvisorList(UUID userId, UUID studentId) {
        Teacher teacher = teacherRepository.findById(userId).orElse(null);
        if (teacher != null) {
            List<Student> advisorStudents = teacher.getAdvisees();

            // Remove the student with the specified studentId from the advisorStudents list
            advisorStudents.removeIf(del_student -> del_student.getUser().getUserId().equals(studentId));

            // Save the updated teacher entity
            teacherRepository.save(teacher);
        }
    }
}
