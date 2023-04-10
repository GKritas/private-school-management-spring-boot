package com.gkritas.privateschoolmanager.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity(name = "assignments")
@Getter
@Setter
@RequiredArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    private String name;
    private String description;
    private LocalDate dueDate;
    private Long maximumScore;
    private Long actualScore;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToMany(mappedBy = "assignments")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
