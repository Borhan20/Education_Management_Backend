package com.edu_manage.education_management.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "emsuser")
public class EMSUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String phone;
    private String name;
    private String password;
    private String status;


        // Getters, setters, constructors
    
    public EMSUser(Long userId, String email, String phone, String name, String password, String status) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.status = status;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String isStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    
}
