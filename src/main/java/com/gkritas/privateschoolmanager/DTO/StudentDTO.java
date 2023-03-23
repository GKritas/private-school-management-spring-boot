package com.gkritas.privateschoolmanager.DTO;

import com.gkritas.privateschoolmanager.domain.Student;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentDTO {
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
        this.courses =student.getCourses().stream()
                .map(CourseDTO::new)
                .toList();
    }
}
