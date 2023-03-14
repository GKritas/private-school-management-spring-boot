package com.gkritas.privateschoolmanager.student;

import com.gkritas.privateschoolmanager.course.Course;
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
@Entity(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private UUID studentId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String guardianFirstName;
    private String guardianLastName;
    private String guardianEmail;
    private String guardianPhoneNumber;
    private LocalDate enrollmentDate;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

}
