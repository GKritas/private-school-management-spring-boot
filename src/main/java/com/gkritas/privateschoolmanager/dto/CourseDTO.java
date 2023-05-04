package com.gkritas.privateschoolmanager.dto;

import com.gkritas.privateschoolmanager.model.Course;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CourseDTO {
    private final String name;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String duration;
    private final double fee;
    private final Long trainerId;
    private final List<StudentDTO> students;
    private final List<AssignmentDTO> assignments;

    public CourseDTO(Course course) {
        name = course.getName();
        description = course.getDescription();
        startDate = course.getStartDate();
        endDate = course.getEndDate();
        duration = course.getDuration();
        fee = course.getFee();
        trainerId = course.getTrainer().getId();
        students = course
                .getStudents()
                .stream()
                .map(StudentDTO::new)
                .toList();
        assignments = course
                .getAssignments()
                .stream()
                .map(AssignmentDTO::new)
                .toList();
    }
}
