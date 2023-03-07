package com.gkritas.privateschoolmanager.trainer;

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

    public Trainer findTrainerById(UUID trainerId) {
        return trainerRepository.findById(trainerId).orElseThrow(()-> new TrainerNotFoundException("Trainer not found with id " + trainerId));
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainerById(UUID trainerId) {
        trainerRepository.deleteById(trainerId);
    }

    public Trainer updateTrainer(Trainer trainer, UUID trainerId) {
        Trainer updatedTrainer = trainerRepository.findById(trainerId)
                .map(trn -> {
                    trn.setFirstName(trainer.getFirstName());
                    trn.setLastName(trainer.getLastName());
                    trn.setDateOfBirth(trainer.getDateOfBirth());
                    trn.setAddress(trainer.getAddress());
                    trn.setEmail(trainer.getEmail());
                    trn.setGender(trainer.getGender());
                    trn.setPhoneNumber(trainer.getPhoneNumber());
                    trn.setSalary(trainer.getSalary());
                    trn.setHireDate(trainer.getHireDate());
                    return trn;
                })
                .orElseGet(()->{
                    trainer.setTrainerId(trainerId);
                    return trainer;
                });

        return trainerRepository.save(updatedTrainer);
    }
}
