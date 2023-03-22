package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.domain.Assignment;
import com.gkritas.privateschoolmanager.modelAssembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.service.AssignmentService;
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
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentModelAssembler assignmentModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Assignment>> getAllAssignments() {

        List<EntityModel<Assignment>> assignments = assignmentService.findAllAssignment().stream()
                .map(assignmentModelAssembler::toModel).toList();

        return CollectionModel.of(assignments, linkTo(methodOn(AssignmentController.class).getAllAssignments()).withSelfRel());
    }


    @GetMapping("/{assignmentId}")
    public EntityModel<Assignment> getSingleAssignment(@PathVariable UUID assignmentId) {
        Assignment assignment = assignmentService.findAssignmentById(assignmentId);

        return assignmentModelAssembler.toModel(assignment);
    }

    @PostMapping
    public ResponseEntity<?> addAssignment(@RequestBody Assignment assignment) {

        Assignment createdAssignment = assignmentService.createAssignment(assignment);

        EntityModel<Assignment> entityModel = assignmentModelAssembler.toModel(createdAssignment);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<?> removeAssignment(@PathVariable UUID assignmentId) {
        assignmentService.deleteAssignmentById(assignmentId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<?> updateAssignment(@PathVariable UUID assignmentId, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(assignmentId, assignment);

        EntityModel<Assignment> entityModel = assignmentModelAssembler.toModel(updatedAssignment);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
