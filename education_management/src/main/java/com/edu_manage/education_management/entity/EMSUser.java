package com.edu_manage.education_management.entity;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private boolean status;
    // private Set<Role> role;


        // Getters, setters, constructors
    
    public EMSUser(Long userId, String email, String phone, String name, String password, boolean status) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.status = status;
        System.out.println("instance created");
    }
    public EMSUser() {
        //TODO Auto-generated constructor stub
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
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
    public void setPassword(String bCryptPasswordEncoder) {
        this.password = bCryptPasswordEncoder;
    }
    public List<Role> getRoles() {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoles'");
    }
    // public void setRoles(Set<Role> singleton) {
    //     this.role = singleton;
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setRoles'");
    // }


    
}
