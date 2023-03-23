package com.gkritas.privateschoolmanager.DTO;

import com.gkritas.privateschoolmanager.domain.Trainer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainerDTO {
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
        this.course = new CourseDTO(trainer.getCourse());
    }
}
