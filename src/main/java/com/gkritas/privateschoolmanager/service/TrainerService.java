package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.exception.TrainerNotFoundException;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer findTrainerById(Long id) {
        return trainerRepository
                .findById(id)
                .orElseThrow(()->
                        new TrainerNotFoundException("Trainer not found with id " + id));
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainerById(Long id) {
        Trainer deletedTrainer = findTrainerById(id);
        trainerRepository.delete(deletedTrainer);
    }

    public Trainer updateTrainer(Trainer trainer, Long id) {
        Trainer updatedTrainer = findTrainerById(id);

        updatedTrainer.setFirstName(trainer.getFirstName());
        updatedTrainer.setLastName(trainer.getLastName());
        updatedTrainer.setAddress(trainer.getAddress());
        updatedTrainer.setEmail(trainer.getEmail());
        updatedTrainer.setPhoneNumber(trainer.getPhoneNumber());
        updatedTrainer.setDateOfBirth(trainer.getDateOfBirth());
        updatedTrainer.setGender(trainer.getGender());
        updatedTrainer.setHireDate(trainer.getHireDate());
        updatedTrainer.setSalary(trainer.getSalary());

        return trainerRepository.save(updatedTrainer);
    }
}
