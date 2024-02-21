package com.edu_manage.education_management.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.edu_manage.education_management.Dto.StudentDTO;
import com.edu_manage.education_management.Dto.TeacherDTO;
import com.edu_manage.education_management.entity.*;
import com.edu_manage.education_management.repository.EMSUserRepository;
import com.edu_manage.education_management.repository.StudentRequestRepository;
import com.edu_manage.education_management.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
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



    public List<?> getActiveStudents() {
        try {
            List<Student> students = studentRepository.findByUser_StatusTrue();
            return students.stream()
                    .map(student -> new StudentDTO(student.getUserId(), student.getUser().getName(), student.getDepartmentName(), student.getStudentId(), student.getBatchNo(),
                            student.getAdvisor().getUser().getName(),student.getUser().getRole().getRole()))
                    .collect(Collectors.toList());
        }
        catch (EntityNotFoundException e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            return null; // or throw a custom exception
        }
    }

    public StudentDTO getStudentById(UUID userId) {
        try {
            StudentDTO studentDTO = new StudentDTO();
            Student student = studentRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Student not found"));

            studentDTO.setUserId(student.getUserId());
            studentDTO.setEmsUserName(student.getUser().getName());
            studentDTO.setDepartment(student.getDepartmentName());
            studentDTO.setStudentId(student.getStudentId());
            studentDTO.setBatchNo(student.getBatchNo());
            studentDTO.setAdvisorName(student.getAdvisor().getUser().getName());
            studentDTO.setRoleName(student.getUser().getRole().getRole());

            return studentDTO;
        } catch (EntityNotFoundException e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            return null; // or throw a custom exception
        }
    }


    public void editProfile(UUID userId, EMSUser updatedStudent) {
        try {
            EMSUser existingStudent = emsUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));
            // Assuming you have a copyProperties method to copy relevant properties
            BeanUtils.copyProperties(updatedStudent, existingStudent, "userId");
            emsUserRepository.save(existingStudent);
        } catch (EntityNotFoundException e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
        }
    }


    public void resetPassword(UUID userId, String newPassword) {
        try {
            EMSUser student = emsUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));
            // Assuming you have a setPassword method in your Student entity
            student.setPassword(newPassword);
            emsUserRepository.save(student);
        } catch (EntityNotFoundException e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
        }
    }


    public void sendAdvisorRequest(UUID userId, UUID teacherId) {
        try {
            Student student = studentRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Student not found"));
            Teacher advisor = teacherRepository.findById(teacherId).orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

            StudentRequest studentRequest = new StudentRequest();
            studentRequest.setStudent(student);
            studentRequest.setTeacher(advisor);
            studentRequest.setStatus(StudentRequestStatus.PENDING);
            studentRequestRepository.save(studentRequest);

            student.setAdvisor(advisor);
            studentRepository.save(student);
        } catch (EntityNotFoundException e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
        }
    }

}
