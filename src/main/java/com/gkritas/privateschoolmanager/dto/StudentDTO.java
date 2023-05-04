package com.gkritas.privateschoolmanager.dto;

import com.gkritas.privateschoolmanager.model.Student;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class StudentDTO {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final LocalDate enrollmentDate;

    private final List<CourseDTO> courses;
    private final List<TrainerDTO> trainers;
    private final List<AssignmentDTO> assignments;

    public StudentDTO(Student student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
        address = student.getAddress();
        email = student.getEmail();
        phoneNumber = student.getPhoneNumber();
        dateOfBirth = student.getDateOfBirth();
        gender = student.getGender();
        enrollmentDate = student.getEnrollmentDate();
        courses = student
                .getCourses()
                .stream()
                .map(CourseDTO::new)
                .toList();
        trainers = student
                .getTrainers()
                .stream()
                .map(TrainerDTO::new)
                .toList();
        assignments = student
                .getAssignments()
                .stream()
                .map(AssignmentDTO::new)
                .toList();
    }
}
