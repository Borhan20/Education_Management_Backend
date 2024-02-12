package com.edu_manage.education_management.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends EMSUser{ 
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private EMSUser user;

    private String departmentName;

    
    private Long advisorId;
    private String studentId;
    private String batchNo;
    
    @ManyToOne
    @JoinColumn(name = "advisor_id", referencedColumnName = "user_id")
    private Teacher advisor;



    
}