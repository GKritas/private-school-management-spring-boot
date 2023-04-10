package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.DTO.TrainerDTO;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.exception.TrainerNotFoundException;
import com.gkritas.privateschoolmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public List<TrainerDTO> findAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream().map(TrainerDTO::new).collect(Collectors.toList());
    }

    public TrainerDTO findTrainerById(Long trainerId) {
        Trainer trainer = trainerRepository
                .findById(trainerId)
                .orElseThrow(()->
                        new TrainerNotFoundException("Trainer not found with id " + trainerId));
        return new TrainerDTO(trainer);
    }

    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setFirstName(trainerDTO.getFirstName());
        trainer.setLastName(trainerDTO.getLastName());
        trainer.setEmail(trainerDTO.getEmail());
        trainer.setAddress(trainerDTO.getAddress());
        trainer.setGender(trainerDTO.getGender());
        trainer.setSalary(trainerDTO.getSalary());
        trainer.setPhoneNumber(trainerDTO.getPhoneNumber());
        trainer.setHireDate(trainerDTO.getHireDate());
        trainer.setDateOfBirth(trainerDTO.getDateOfBirth());
        trainerRepository.save(trainer);
        trainerDTO.setTrainerId(trainer.getTrainerId());
        return new TrainerDTO(trainer);
    }

    public void deleteTrainerById(Long trainerId) {
        trainerRepository.deleteById(trainerId);
    }

    public TrainerDTO updateTrainer(TrainerDTO trainer, Long trainerId) {
        Trainer updatedTrainer = trainerRepository
                .findById(trainerId)
                .orElseThrow(()->
                        new TrainerNotFoundException("Trainer not found with id " + trainerId));

        updatedTrainer.setFirstName(trainer.getFirstName());
        updatedTrainer.setLastName(trainer.getLastName());
        updatedTrainer.setAddress(trainer.getAddress());
        updatedTrainer.setEmail(trainer.getEmail());
        updatedTrainer.setPhoneNumber(trainer.getPhoneNumber());
        updatedTrainer.setDateOfBirth(trainer.getDateOfBirth());
        updatedTrainer.setGender(trainer.getGender());
        updatedTrainer.setHireDate(trainer.getHireDate());
        updatedTrainer.setSalary(trainer.getSalary());

        return new TrainerDTO(trainerRepository.save(updatedTrainer));
    }
}
