package com.gkritas.privateschoolmanager.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public CollectionModel<EntityModel<Trainer>> getAllTrainers() {
        List<EntityModel<Trainer>> trainers = trainerService.findAllTrainers().stream()
                .map(trainerModelAssembler::toModel).toList();

        return CollectionModel.of(trainers, linkTo(methodOn(TrainerController.class).getAllTrainers()).withSelfRel());
    }

    @GetMapping("/{trainerId}")
    public EntityModel<Trainer> getSingleTrainer(@PathVariable UUID trainerId) {
        Trainer trainer = trainerService.findTrainerById(trainerId);

        return trainerModelAssembler.toModel(trainer);
    }

    @PostMapping
    public ResponseEntity<?> addTrainer(@RequestBody Trainer trainer) {
        EntityModel<Trainer> entityModel = trainerModelAssembler.toModel(trainerService.createTrainer(trainer));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{trainerId}")
    public ResponseEntity<?> removeTrainer(@PathVariable UUID trainerId) {
        trainerService.deleteTrainerById(trainerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{trainerId}")
    public ResponseEntity<?> updateTrainer(@PathVariable UUID trainerId, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(trainer, trainerId);

        EntityModel<Trainer> entityModel = trainerModelAssembler.toModel(updatedTrainer);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
