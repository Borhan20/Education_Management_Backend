package com.edu_manage.education_management.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends EMSUser{
    public Teacher(Long userId, String email, String phone, String name, String password, boolean status) {
        super(userId, email, phone, name, password, status);
        //TODO Auto-generated constructor stub
    }
    @Id
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private EMSUser user;

    private String facultyName;
    private String designation;

    

    
    // Getters, setters, constructors
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public EMSUser getUser() {
        return user;
    }
    public void setUser(EMSUser user) {
        this.user = user;
    }
    public String getFacultyName() {
        return facultyName;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    
    
}
