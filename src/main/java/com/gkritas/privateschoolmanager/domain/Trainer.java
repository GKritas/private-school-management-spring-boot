package com.gkritas.privateschoolmanager.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@Entity(name = "trainers")
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate hireDate;
    private Long salary;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
