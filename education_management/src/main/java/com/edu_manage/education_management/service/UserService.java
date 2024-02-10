package com.edu_manage.education_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu_manage.education_management.repository.EMSUserRepository;

@Service
public class UserService {
    @Autowired
    private EMSUserRepository userRepository;
}
