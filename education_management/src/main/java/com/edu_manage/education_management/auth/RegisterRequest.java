package com.edu_manage.education_management.auth;

import com.edu_manage.education_management.entity.Role;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String phone;
    private String name;
    private String password;
}