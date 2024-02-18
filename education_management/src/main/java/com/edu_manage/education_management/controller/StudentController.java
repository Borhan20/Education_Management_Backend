package com.edu_manage.education_management.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public String getTest(){
        return "tested successfully";
    }

    // Implement endpoints for teacher-related operations
//    @GetMapping("/active-teachers")
//
////    public List<Teacher> getActiveTeachers() {
////        return teacherService.getActiveTeachers();
////    }
    @GetMapping("/active-teachers")
    public ResponseEntity<?> getActiveTeachers() {

        TeacherDTO teacherDTO = new TeacherDTO();

        List<Teacher> teacherList = teacherService.getActiveTeachers();

        List<TeacherDTO> teacherDTOList = teacherList.stream()
                .map(teacher -> new TeacherDTO(teacher.getUserId(), teacher.getFacultyName(), teacher.getDesignation(), teacher.getTeacherId(),teacher.getUser()))
                .collect(Collectors.toList());

//        teacherDTO.setFacultyName(teacherList.get(0).getFacultyName());
//        //teacherDTO.setEmsUserId(teacherList.get(0).getUserId());
//        teacherDTO.setTeacherId(teacherList.get(0).getTeacherId());
        System.out.println(teacherList.get(0).getFacultyName());
        return ResponseEntity.ok(teacherDTOList);
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
    public ResponseEntity<String> sendAdvisorRequest(@PathVariable UUID userId, @RequestParam UUID teacherId) {
        studentService.sendAdvisorRequest(userId, teacherId);
        return ResponseEntity.ok("Advisor request sent successfully");
    }

}
