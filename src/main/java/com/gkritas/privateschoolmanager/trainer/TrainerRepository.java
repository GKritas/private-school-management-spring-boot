package com.gkritas.privateschoolmanager.trainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
    Trainer findByUsername(String username);
}
