package com.gkritas.privateschoolmanager.repository;

import com.gkritas.privateschoolmanager.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
