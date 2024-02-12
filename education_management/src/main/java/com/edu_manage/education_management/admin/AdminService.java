package com.edu_manage.education_management.admin;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
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

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addRoleToUser(Long userId, String roleName) {
        EMSUser user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Role role = roleRepository.findByRole(roleName).orElseThrow(() -> new EntityNotFoundException("Role not found"));

        user.getRole().add(role);
        userRepository.save(user);
    }

    public void deactivateUser(Long userId) {
        EMSUser user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
    }
}
