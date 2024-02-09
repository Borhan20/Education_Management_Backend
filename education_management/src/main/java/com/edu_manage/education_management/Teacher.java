package com.edu_manage.education_management;

import org.springframework.stereotype.Component;

import com.edu_manage.education_management.EMSUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Teacher {
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
