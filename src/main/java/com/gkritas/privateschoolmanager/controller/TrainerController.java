package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.TrainerDTO;
import com.gkritas.privateschoolmanager.domain.Trainer;
import com.gkritas.privateschoolmanager.modelAssembler.TrainerModelAssembler;
import com.gkritas.privateschoolmanager.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerModelAssembler trainerModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<TrainerDTO>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        List<EntityModel<TrainerDTO>> trainerModels = trainers.stream()
                .map(trainerModelAssembler::toModel)
                .toList();

        return CollectionModel.of(trainerModels,
                linkTo(methodOn(TrainerController.class).getAllTrainers()).withSelfRel());
    }

    @GetMapping("/{trainerId}")
    public EntityModel<TrainerDTO> getSingleTrainer(@PathVariable Long trainerId) {
        Trainer trainer = trainerService.findTrainerById(trainerId);
        return trainerModelAssembler.toModel(trainer);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TrainerDTO>> addTrainer(@RequestBody Trainer trainer) {
        Trainer createdTrainer = trainerService.createTrainer(trainer);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(createdTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }

    @DeleteMapping("/{trainerId}")
    public ResponseEntity<?> removeTrainer(@PathVariable Long trainerId) {
        trainerService.deleteTrainerById(trainerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{trainerId}")
    public EntityModel<TrainerDTO> updateTrainer(@PathVariable Long trainerId, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(trainer, trainerId);
        return trainerModelAssembler.toModel(updatedTrainer);
    }
}
