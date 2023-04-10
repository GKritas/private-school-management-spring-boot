package com.gkritas.privateschoolmanager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.model.Trainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CourseDTO {
    @JsonIgnore
    private Long courseId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private double fee;

    private List<StudentDTO> students;
    private List<AssignmentDTO> assignments;
    private TrainerDTO trainer;

    public CourseDTO(Course course) {
        this.courseId = course.getCourseId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.duration = course.getDuration();
        this.fee = course.getFee();
        this.students = course.getStudents().stream()
                .map(StudentDTO::new).collect(Collectors.toList());
        this.assignments = course.getAssignments().stream()
                .map(AssignmentDTO::new).collect(Collectors.toList());
        if (course.getTrainer() != null) {
            this.trainer = new TrainerDTO(course.getTrainer());
        } else {
            this.trainer = new TrainerDTO();
        }

    }
}
