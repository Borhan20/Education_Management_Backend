package com.edu_manage.education_management.service;


import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.edu_manage.education_management.Dto.StudentDTO;
import com.edu_manage.education_management.Dto.StudentRequestDTO;
import com.edu_manage.education_management.Dto.TeacherDTO;
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

    public List<?> getActiveTeachers() {
        try {
            List<Teacher> teacherList = teacherRepository.findByUser_StatusTrue();

            return teacherList.stream()
                    .map(teacher -> new TeacherDTO(teacher.getUserId(), teacher.getFacultyName(), teacher.getDesignation(), teacher.getTeacherId(), teacher.getUser().getName()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
            return Collections.emptyList(); // or any appropriate response
        }
    }


    public TeacherDTO getTeacherById(UUID userId) {
        try {
            TeacherDTO teacherDTO = new TeacherDTO();
            Teacher teacher = teacherRepository.findById(userId).orElse(null);

            if (teacher != null) {
                teacherDTO.setUserId(teacher.getUserId());
                teacherDTO.setEmsUserName(teacher.getUser().getName());
                teacherDTO.setTeacherId(teacher.getTeacherId());
                teacherDTO.setDesignation(teacher.getDesignation());
                teacherDTO.setFacultyName(teacher.getFacultyName());
            }

            return teacherDTO;
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
            return null; // or any appropriate response
        }
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


    public List<?> getStudentRequests(UUID userId) {
        try {
            StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
            List<StudentRequest> studentRequestsList = studentRequestRepository.findByTeacherUserIdAndStatus(userId, StudentRequestStatus.PENDING);

            return studentRequestsList.stream()
                    .map(studentRequest -> new StudentRequestDTO(studentRequest.getReuquestId(), studentRequest.getStudent().getUser().getName(),
                            studentRequest.getTeacher().getUser().getName(), studentRequest.getStatus()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
            return null; // or any appropriate response
        }
    }



    public void acceptStudentRequest(UUID userId, UUID studentId) {
        StudentRequest new_request = studentRequestRepository.findByStudentUserId(studentId).orElse(null);
        if (new_request != null && new_request.getTeacher().getUserId().equals(userId) && new_request.getStatus() == StudentRequestStatus.PENDING) {
            new_request.setStatus(StudentRequestStatus.ACCEPTED);
            studentRequestRepository.save(new_request);
        }
    }


    public void dismissStudentRequest(UUID userId, UUID studentId) {
        try {
            StudentRequest new_request = studentRequestRepository.findByStudentUserId(studentId).orElse(null);

            if (new_request != null && new_request.getTeacher().getUserId().equals(userId) && new_request.getStatus() == StudentRequestStatus.PENDING) {
                new_request.setStatus(StudentRequestStatus.DISMISSED);
                studentRequestRepository.save(new_request);
            }
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
        }
    }



    public List<?> getAdvisorStudents(UUID userId) {
        try {
            List<Student> students = studentRepository.findByAdvisorUserId(userId);

            return students.stream()
                    .map(student -> new StudentDTO(student.getUserId(), student.getUser().getName(), student.getDepartmentName(), student.getStudentId(), student.getBatchNo(), student.getAdvisor()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
            return Collections.emptyList(); // or return an appropriate error response
        }
    }


    public void removeStudentFromAdvisorList(UUID userId, UUID studentId) {
        try {
            Teacher teacher = teacherRepository.findById(userId).orElse(null);
            if (teacher != null) {
                List<Student> advisorStudents = teacher.getAdvisees();

                // Remove the student with the specified studentId from the advisorStudents list
                advisorStudents.removeIf(del_student -> del_student.getUser().getUserId().equals(studentId));

                // Save the updated teacher entity
                teacherRepository.save(teacher);
            }
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an appropriate error response
        }
    }

}
