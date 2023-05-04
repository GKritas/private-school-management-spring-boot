package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.service.AssignmentService;
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
@RequestMapping("/api/v1/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentModelAssembler assignmentModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.findAllAssignment();
        List<EntityModel<Assignment>> assignmentModels = assignments.stream()
                .map(assignmentModelAssembler::toModel)
                .toList();
        return CollectionModel.of(assignmentModels,
                linkTo(methodOn(AssignmentController.class).getAllAssignments()).withSelfRel());
    }


    @GetMapping("/{id}")
    public EntityModel<Assignment> getSingleAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findAssignmentById(id);

        return assignmentModelAssembler.toModel(assignment);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Assignment>> addAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.saveAssignment(assignment);
        EntityModel<Assignment> assignmentModel = assignmentModelAssembler.toModel(createdAssignment);
        return ResponseEntity
                .created(assignmentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(assignmentModel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public EntityModel<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(id, assignment);

        return assignmentModelAssembler.toModel(updatedAssignment);
    }
}
