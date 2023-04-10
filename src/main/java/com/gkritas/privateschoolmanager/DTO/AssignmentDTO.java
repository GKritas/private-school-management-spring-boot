package com.gkritas.privateschoolmanager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkritas.privateschoolmanager.model.Assignment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AssignmentDTO {
    @JsonIgnore
    private Long assignmentId;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Long maximumScore;
    private Long actualScore;
    private CourseDTO course;
    private List<StudentDTO> students;

    private TrainerDTO trainer;

    public AssignmentDTO(Assignment assignment) {
        this.assignmentId = assignment.getAssignmentId();
        this.name = assignment.getName();
        this.description = assignment.getDescription();
        this.dueDate = assignment.getDueDate();
        this.maximumScore = assignment.getMaximumScore();
        this.actualScore = assignment.getActualScore();
        if (assignment.getCourse() != null) {
            this.course = new CourseDTO(assignment.getCourse());
        } else {
            this.course = new CourseDTO();
        }

        this.students = assignment.getStudents().stream()
                .map(StudentDTO::new).toList();
        if (assignment.getTrainer() != null) {
            this.trainer = new TrainerDTO(assignment.getTrainer());
        } else {
            this.trainer = new TrainerDTO();
        }

    }

}
