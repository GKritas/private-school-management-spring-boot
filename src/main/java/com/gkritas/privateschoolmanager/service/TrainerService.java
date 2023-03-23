package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.domain.Trainer;
import com.gkritas.privateschoolmanager.exception.TrainerNotFoundException;
import com.gkritas.privateschoolmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer findTrainerById(Long trainerId) {
        return trainerRepository.findById(trainerId).orElseThrow(()-> new TrainerNotFoundException("Trainer not found with id " + trainerId));
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainerById(Long trainerId) {
        Trainer trainer = findTrainerById(trainerId);
        trainerRepository.delete(trainer);
    }

    public Trainer updateTrainer(Trainer trainer, Long trainerId) {
        Trainer updatedTrainer = findTrainerById(trainerId);

        updatedTrainer.setFirstName(trainer.getFirstName());
        updatedTrainer.setLastName(trainer.getLastName());
        updatedTrainer.setAddress(trainer.getAddress());
        updatedTrainer.setEmail(trainer.getEmail());
        updatedTrainer.setPhoneNumber(trainer.getPhoneNumber());
        updatedTrainer.setDateOfBirth(trainer.getDateOfBirth());
        updatedTrainer.setGender(trainer.getGender());
        updatedTrainer.setHireDate(trainer.getHireDate());
        updatedTrainer.setSalary(trainer.getSalary());
        updatedTrainer.setCourse(trainer.getCourse());

        return trainerRepository.save(updatedTrainer);
    }
}
