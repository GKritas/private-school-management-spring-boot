package com.gkritas.privateschoolmanager.DTO;

import com.gkritas.privateschoolmanager.domain.Course;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDTO {
    private Long courseId;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private double fee;

    public CourseDTO(Course course) {
        this.courseId = course.getCourseId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.duration = course.getDuration();
        this.fee = course.getFee();
    }
}
