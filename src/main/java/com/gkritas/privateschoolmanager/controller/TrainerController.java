package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.TrainerModelAssembler;
import com.gkritas.privateschoolmanager.model.Trainer;
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
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerModelAssembler trainerModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        List<EntityModel<Trainer>> trainerModels = trainers.stream()
                .map(trainerModelAssembler::toModel)
                .toList();

        return CollectionModel.of(trainerModels,
                linkTo(methodOn(TrainerController.class).getAllTrainers()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Trainer> getSingleTrainer(@PathVariable Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        return trainerModelAssembler.toModel(trainer);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Trainer>> addTrainer(@RequestBody Trainer trainer) {
        Trainer createdTrainer = trainerService.createTrainer(trainer);
        EntityModel<Trainer> trainerModel = trainerModelAssembler.toModel(createdTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTrainer(@PathVariable Long id) {
        trainerService.deleteTrainerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public EntityModel<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(trainer, id);
        return trainerModelAssembler.toModel(updatedTrainer);
    }
}
