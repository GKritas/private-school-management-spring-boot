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
public class StudentDTO {
    @JsonIgnore
    private Long studentId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate enrollmentDate;
    private List<CourseDTO> courses;
    private List<AssignmentDTO> assignments;
    private List<TrainerDTO> trainers;

    public StudentDTO(Student student) {
        this.studentId = student.getStudentId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.address = student.getAddress();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
        this.dateOfBirth = student.getDateOfBirth();
        this.gender = student.getGender();
        this.enrollmentDate = student.getEnrollmentDate();
        this.courses = student.getCourses().stream()
                .map(CourseDTO::new).collect(Collectors.toList());
        this.assignments = student.getAssignments().stream()
                .map(AssignmentDTO::new).collect(Collectors.toList());
        this.trainers = student.getTrainers().stream()
                .map(TrainerDTO::new).collect(Collectors.toList());
    }
}
