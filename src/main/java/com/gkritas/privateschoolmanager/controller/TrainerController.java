package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.TrainerModelAssembler;
import com.gkritas.privateschoolmanager.dto.TrainerDTO;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.service.TrainerService;
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

    private final TrainerService trainerService;

    private final TrainerModelAssembler trainerModelAssembler;

    public TrainerController(TrainerService trainerService, TrainerModelAssembler trainerModelAssembler) {
        this.trainerService = trainerService;
        this.trainerModelAssembler = trainerModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TrainerDTO>>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        List<EntityModel<TrainerDTO>> trainerModels = trainers.stream()
                .map(trainerModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(trainerModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TrainerDTO>> getSingleTrainer(@PathVariable Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(trainer);

        return ResponseEntity.ok(trainerModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TrainerDTO>> createTrainer(@RequestBody Trainer trainer) {
        Trainer createdTrainer = trainerService.saveTrainer(trainer);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(createdTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TrainerDTO>> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(trainer, id);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(updatedTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }
}
