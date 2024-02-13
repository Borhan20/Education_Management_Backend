package com.edu_manage.education_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.edu_manage.education_management.entity")
public class EducationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationManagementApplication.class, args);
		System.out.println("hello world");
	}

}
