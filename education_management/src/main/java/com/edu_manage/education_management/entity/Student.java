package com.edu_manage.education_management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends EMSUser{
    
    public Student(Long userId, String email, String phone, String name, String password, boolean status) {
        super(userId, email, phone, name, password, status);
        //TODO Auto-generated constructor stub
    }
    @Id
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private EMSUser user;

    private String departmentName;
    private Long advisorId;
    private String studentId;
    private String batchNo;

    

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
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getAdvisorId() {
        return advisorId;
    }
    public void setAdvisorId(Long advisorId) {
        this.advisorId = advisorId;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }


    
}