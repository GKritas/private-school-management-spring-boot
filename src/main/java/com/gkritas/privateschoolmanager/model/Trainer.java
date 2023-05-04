package com.gkritas.privateschoolmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "trainer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate hireDate;
    private Long salary;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Assignment> assignments;
}
