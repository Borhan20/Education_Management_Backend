package com.edu_manage.education_management;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Student {
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