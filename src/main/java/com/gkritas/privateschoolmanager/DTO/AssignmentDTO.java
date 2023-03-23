package com.gkritas.privateschoolmanager.DTO;

import com.gkritas.privateschoolmanager.domain.Assignment;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AssignmentDTO {
    private Long assignmentId;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Long maximumScore;
    private Long actualScore;
    private CourseDTO course;
    private List<StudentDTO> students;

    public AssignmentDTO(Assignment assignment) {
        this.assignmentId = assignment.getAssignmentId();
        this.name = assignment.getName();
        this.description = assignment.getDescription();
        this.dueDate = assignment.getDueDate();
        this.maximumScore = assignment.getMaximumScore();
        this.actualScore = assignment.getActualScore();
        this.course = new CourseDTO(assignment.getCourse());
        this.students = assignment.getStudents().stream()
                .map(StudentDTO::new).toList();
    }

}
