package com.gkritas.privateschoolmanager.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gkritas.privateschoolmanager.model.Trainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TrainerDTO {
    @JsonIgnore
    private Long trainerId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate hireDate;
    private Long salary;
    private CourseDTO course;
    private List<StudentDTO> students;
    private List<AssignmentDTO> assignments;

    public TrainerDTO(Trainer trainer) {
        this.trainerId = trainer.getTrainerId();
        this.firstName = trainer.getFirstName();
        this.lastName = trainer.getLastName();
        this.address = trainer.getAddress();
        this.email = trainer.getEmail();
        this.phoneNumber = trainer.getPhoneNumber();
        this.dateOfBirth = trainer.getDateOfBirth();
        this.gender = trainer.getGender();
        this.hireDate = trainer.getHireDate();
        this.salary = trainer.getSalary();
        if (trainer.getCourse() != null) {
            this.course = new CourseDTO(trainer.getCourse());
        } else {
            this.course = new CourseDTO();
        }
        this.students = trainer.getStudents().stream()
                .map(StudentDTO::new).collect(Collectors.toList());
        this.assignments = trainer.getAssignments().stream()
                .map(AssignmentDTO::new).collect(Collectors.toList());
    }
}
