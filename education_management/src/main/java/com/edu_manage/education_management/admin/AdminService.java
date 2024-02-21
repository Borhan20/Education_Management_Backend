package com.edu_manage.education_management.admin;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.edu_manage.education_management.Dto.StudentDTO;
import com.edu_manage.education_management.Dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.entity.EMSUser;
import com.edu_manage.education_management.entity.Role;
import com.edu_manage.education_management.entity.Student;
import com.edu_manage.education_management.entity.Teacher;
import com.edu_manage.education_management.repository.EMSUserRepository;
import com.edu_manage.education_management.repository.RoleRepository;
import com.edu_manage.education_management.repository.StudentRepository;
import com.edu_manage.education_management.repository.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {
    @Autowired
    private EMSUserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<?> getAllTeachers() {

        try {
            List<Teacher> teacherList = teacherRepository.findByUser_StatusTrue();

            return teacherList.stream()
                    .map(teacher -> new TeacherDTO(teacher.getUserId(), teacher.getFacultyName(), teacher.getDesignation(), teacher.getTeacherId(),
                            teacher.getUser().getName(),teacher.getUser().getRole().getRole()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an error response
            return Collections.emptyList(); // or any appropriate response
        }
    }

    public List<?> getAllStudents() {

        try {
            List<Student> students = studentRepository.findAll();
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

    // creating a new student with student role
    public void createStudents(UUID userId, String email, String phone, String name,
                               String password, String department, String studentId, String batchNo) {
        try {
            Role studentRole = roleRepository.findByRole("STUDENT").orElseGet(() -> {
                Role role = new Role();
                role.setRole("STUDENT");
                role.setDescription("Student Role");
                return roleRepository.save(role);
            });

            EMSUser emsUser = userRepository.findByEmail(email).orElseGet(() -> {
                EMSUser user = new EMSUser();
                user.setUserId(userId);
                user.setEmail(email);
                user.setName(name);
                user.setPhone(phone);
                // Set other user details
                user.setPassword(passwordEncoder.encode(password));
                user.setStatus(true);
                user.setRole(studentRole);
                return userRepository.save(user);
            });

            Student student = studentRepository.findByStudentId(studentId).orElseGet(() -> {
                Student new_student = new Student();
                new_student.setUserId(userId);
                new_student.setStudentId(studentId);
                new_student.setBatchNo(batchNo);
                new_student.setDepartmentName(department);
                new_student.setUser(emsUser);
                return studentRepository.save(new_student);
            });
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an appropriate error response
        }
    }



    // creating a  new teacher as advisor role
    public void createTeachers(UUID userId,String email,String phone,String name,
                               String password,String faculty,String designation,
                               String teacherId){


        Role studentRole = roleRepository.findByRole("STUDENT").orElseGet(() -> {
            Role role = new Role();
            role.setRole("TEACHER");
            role.setDescription("teacher Role");
            return roleRepository.save(role);
        });


        EMSUser emsUser = userRepository.findByEmail(email).orElseGet(() -> {
            EMSUser user = new EMSUser();
            user.setUserId(userId);
            user.setEmail(email);
            user.setName(name);
            user.setPhone(phone);
            // Set other user details
            user.setPassword(passwordEncoder.encode(password));
            user.setStatus(true);
            user.setRole(studentRole);
            return userRepository.save(user);

        });

        Teacher teacher = teacherRepository.findByTeacherId(teacherId).orElseGet(() ->{
            Teacher new_teacher = new Teacher();
            new_teacher.setUserId(userId);
            new_teacher.setFacultyName(faculty);
            new_teacher.setDesignation(designation);
            new_teacher.setTeacherId(teacherId);
            new_teacher.setUser(emsUser);
            return teacherRepository.save(new_teacher);
        });
    }

    public void addRoleToUser(UUID userId, String roleName) {
        try {
            EMSUser user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Role role = roleRepository.findByRole(roleName).orElseThrow(() -> new EntityNotFoundException("Role not found"));

            if ("STUDENT".equals(roleName) && "TEACHER".equals(user.getRole().getRole())) {
                // New student entry
                Student newStudent = new Student();
                newStudent.setUserId(userId);
                newStudent.setUser(user);

                // Delete old teacher entry
                teacherRepository.deleteByUserId(userId);
            }

            if ("TEACHER".equals(roleName) && "STUDENT".equals(user.getRole().getRole())) {
                // New teacher entry
                Teacher newTeacher = new Teacher();
                newTeacher.setUserId(userId);
                newTeacher.setUser(user);

                // Delete old student entry
                studentRepository.deleteByUserId(userId);
            }

            user.getRole().add(role);
            userRepository.save(user);
        } catch (Exception e) {
            // Handle exception, you can log it or throw a custom exception if needed
            e.printStackTrace(); // log or handle the exception as per your requirement
            // You might also want to throw a custom exception or return an appropriate error response
        }
    }

    public void deactivateUser(UUID userId) {
        EMSUser user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
    }
}
