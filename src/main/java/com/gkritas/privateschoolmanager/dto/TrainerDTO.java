package com.gkritas.privateschoolmanager.dto;

import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Trainer;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TrainerDTO {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final LocalDate hireDate;
    private final Long salary;

    private final Long courseId;
    private final List<StudentDTO> students;
    private final List<AssignmentDTO> assignments;

    public TrainerDTO(Trainer trainer) {
        firstName = trainer.getFirstName();
        lastName = trainer.getLastName();
        address = trainer.getAddress();
        email = trainer.getEmail();
        phoneNumber = trainer.getPhoneNumber();
        dateOfBirth = trainer.getDateOfBirth();
        gender = trainer.getGender();
        hireDate = trainer.getHireDate();
        salary = trainer.getSalary();

        courseId = trainer.getCourse().getId();
        students = trainer
                .getStudents()
                .stream()
                .map(StudentDTO::new)
                .toList();
        assignments = trainer
                .getAssignments()
                .stream()
                .map(AssignmentDTO::new)
                .toList();
    }
}
