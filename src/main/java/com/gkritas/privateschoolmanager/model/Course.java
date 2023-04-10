package com.gkritas.privateschoolmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity(name = "courses")
@Getter
@Setter
@RequiredArgsConstructor
public class Course  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private double fee;
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments;

    @OneToOne(mappedBy = "course")
    private Trainer trainer;

}
