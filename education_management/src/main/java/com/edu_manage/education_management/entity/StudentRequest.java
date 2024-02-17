package com.edu_manage.education_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  reuquestId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private StudentRequestStatus status;


}
