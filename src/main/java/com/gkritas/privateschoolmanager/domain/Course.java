package com.gkritas.privateschoolmanager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Data
@Builder
@Entity(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course  {
    @Id
    @GeneratedValue
    private UUID courseId;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private double fee;

}
