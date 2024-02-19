package com.edu_manage.education_management.controller;

import com.edu_manage.education_management.Dto.TeacherDTO;
import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.StudentRequest;
import com.edu_manage.education_management.repository.StudentRepository;
import com.edu_manage.education_management.repository.StudentRequestRepository;
import com.edu_manage.education_management.repository.TeacherRepository;
import com.edu_manage.education_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private  TeacherService teacherService;

    @Autowired
    private  StudentService studentService;


    // Implement endpoints for teacher-related operations
//    @GetMapping("/active-teachers")
//    public List<Teacher> getActiveTeachers() {
//
//        return teacherService.getActiveTeachers();
//    }

    @GetMapping("/active-teachers")
    public ResponseEntity<?> getActiveTeachers() {
        return ResponseEntity.ok(teacherService.getActiveTeachers());
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> viewProfile(@PathVariable UUID userId) {

        return ResponseEntity.ok(teacherService.getTeacherById(userId));
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<String> editProfile(@PathVariable UUID userId, @RequestBody Teacher updatedTeacher) {
        teacherService.editProfile(userId, updatedTeacher);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @PostMapping("/{userId}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable UUID userId, @RequestParam String newPassword) {
        teacherService.resetPassword(userId, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    @GetMapping("/{userId}/student-requests")
    public ResponseEntity<?> getStudentRequests(@PathVariable UUID userId) {

        return ResponseEntity.ok(teacherService.getStudentRequests(userId));
    }

    @PostMapping("/{userId}/accept-request")
    public ResponseEntity<String> acceptStudentRequest(@PathVariable UUID userId, @RequestParam UUID studentId) {
        teacherService.acceptStudentRequest(userId, studentId);
        return ResponseEntity.ok("Student request accepted");
    }

    @PostMapping("/{userId}/dismiss-request")
    public ResponseEntity<String> dismissStudentRequest(@PathVariable UUID userId, @RequestParam UUID studentId) {
        teacherService.dismissStudentRequest(userId, studentId);
        return ResponseEntity.ok("Student request dismissed");
    }

    @GetMapping("/{userId}/advisor-students")
    public ResponseEntity<?> getAdvisorStudents(@PathVariable UUID userId) {
        return ResponseEntity.ok(teacherService.getAdvisorStudents(userId));
    }

    @PostMapping("/{userId}/remove-student")
    public ResponseEntity<String> removeStudentFromAdvisorList(@PathVariable UUID userId, @RequestParam UUID studentId) {
        teacherService.removeStudentFromAdvisorList(userId, studentId);
        return ResponseEntity.ok("Student removed from advisor list");
    }
}
