package com.gkritas.privateschoolmanager.domain;

import com.gkritas.privateschoolmanager.domain.Course;
import com.gkritas.privateschoolmanager.domain.Student;
import com.gkritas.privateschoolmanager.domain.Trainer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity(name = "assignments")
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue
    private UUID assignmentId;

    private String name;
    private String description;
    private LocalDate dueDate;
    private Long maximumScore;
    private Long actualScore;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToMany
    @JoinTable(name = "assignment_student",
            joinColumns = @JoinColumn(name = "assignment_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
