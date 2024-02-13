package com.edu_manage.education_management.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student  {


    @Id
    @Column(name = "user_id")
    private UUID userId;


    @OneToOne
    @JoinColumn(name = "ems_user_id", referencedColumnName = "user_id")
    private EMSUser user;

    private String departmentName;



    private String batchNo;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Teacher advisor;



}