package com.gkritas.privateschoolmanager.dto;

import com.gkritas.privateschoolmanager.model.Assignment;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class AssignmentDTO {

    private final String name;
    private final String description;
    private final LocalDate dueDate;
    private final Long maximumScore;
    private final Long actualScore;

    private final Long courseId;
    private final Long trainerId;
    private final List<StudentDTO> students;

    public AssignmentDTO(Assignment assignment) {
        name = assignment.getName();
        description = assignment.getDescription();
        dueDate = assignment.getDueDate();
        maximumScore = assignment.getMaximumScore();
        actualScore = assignment.getActualScore();

        courseId = assignment.getCourse().getId();
        trainerId = assignment.getTrainer().getId();
        students = assignment
                .getStudents()
                .stream()
                .map(StudentDTO::new)
                .toList();
    }
}
