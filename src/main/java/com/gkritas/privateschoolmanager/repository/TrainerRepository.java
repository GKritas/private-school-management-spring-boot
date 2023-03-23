package com.gkritas.privateschoolmanager.repository;

import com.gkritas.privateschoolmanager.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
