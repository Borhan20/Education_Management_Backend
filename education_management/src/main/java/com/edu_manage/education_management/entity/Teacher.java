package com.edu_manage.education_management.entity;

import java.util.Set;
import java.util.UUID;


import org.springframework.stereotype.Component;
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

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher{

    @Id
    @Column(name = "user_id")
    private UUID userId;


    @OneToOne
    @JoinColumn(name = "ems_user_id", referencedColumnName = "user_id")
    private EMSUser user;

    private String facultyName;
    private String designation;
    private String teacherId;

    // Relationships
    @OneToMany(mappedBy = "advisor")
    private Set<Student> advisees;
   
    
}
