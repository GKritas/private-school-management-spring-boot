package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.TrainerModelAssembler;
import com.gkritas.privateschoolmanager.dto.AssignmentDTO;
import com.gkritas.privateschoolmanager.dto.StudentDTO;
import com.gkritas.privateschoolmanager.dto.TrainerDTO;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.service.TrainerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    private final TrainerModelAssembler trainerModelAssembler;
    private final StudentModelAssembler studentModelAssembler;
    private final AssignmentModelAssembler assignmentModelAssembler;

    public TrainerController(TrainerService trainerService,
                             TrainerModelAssembler trainerModelAssembler,
                             StudentModelAssembler studentModelAssembler,
                             AssignmentModelAssembler assignmentModelAssembler) {
        this.trainerService = trainerService;
        this.trainerModelAssembler = trainerModelAssembler;
        this.studentModelAssembler = studentModelAssembler;
        this.assignmentModelAssembler = assignmentModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TrainerDTO>>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        List<EntityModel<TrainerDTO>> trainerModels = trainers.stream()
                .map(trainerModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(trainerModels));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<TrainerDTO>> getSingleTrainer(@PathVariable Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(trainer);

        return ResponseEntity.ok(trainerModel);
    }

    @GetMapping("{id}/students")
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudentsForTrainer(@PathVariable Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        List<Student> students = trainer.getStudents();
        List<EntityModel<StudentDTO>> studentModels = students
                .stream()
                .map(studentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(studentModels));
    }

    @GetMapping("{id}/assignments")
    public ResponseEntity<CollectionModel<EntityModel<AssignmentDTO>>> getAssignmentsForTrainer(@PathVariable Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        List<Assignment> assignments = trainer.getAssignments();
        List<EntityModel<AssignmentDTO>> assignmentModels = assignments
                .stream()
                .map(assignmentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(assignmentModels));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TrainerDTO>> createTrainer(@RequestBody Trainer trainer) {
        Trainer createdTrainer = trainerService.saveTrainer(trainer);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(createdTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<TrainerDTO>> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        Trainer updatedTrainer = trainerService.updateTrainer(trainer, id);
        EntityModel<TrainerDTO> trainerModel = trainerModelAssembler.toModel(updatedTrainer);

        return ResponseEntity.created(trainerModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(trainerModel);
    }
}
