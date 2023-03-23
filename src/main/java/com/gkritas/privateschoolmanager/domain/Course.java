package com.gkritas.privateschoolmanager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@Entity(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private double fee;

}
