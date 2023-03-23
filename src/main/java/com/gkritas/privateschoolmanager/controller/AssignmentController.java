package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.AssignmentDTO;
import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.domain.Assignment;
import com.gkritas.privateschoolmanager.domain.Student;
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
    public CollectionModel<EntityModel<AssignmentDTO>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.findAllAssignment();
        List<EntityModel<AssignmentDTO>> assignmentModels = assignments.stream()
                .map(assignmentModelAssembler::toModel)
                .toList();
        return CollectionModel.of(assignmentModels,
                linkTo(methodOn(AssignmentController.class).getAllAssignments()).withSelfRel());
    }


    @GetMapping("/{assignmentId}")
    public EntityModel<AssignmentDTO> getSingleAssignment(@PathVariable Long assignmentId) {
        Assignment assignment = assignmentService.findAssignmentById(assignmentId);

        return assignmentModelAssembler.toModel(assignment);
    }

    @GetMapping("/{assignmentId}/students")
    public CollectionModel<EntityModel<StudentDTO>> getAssignmentStudents(@PathVariable Long assignmentId) {
        List<Student> students = assignmentService.findAssignmentById(assignmentId).getStudents();
        List<EntityModel<StudentDTO>> studentModels = students.stream()
                .map(student -> EntityModel.of(new StudentDTO(student)))
                .toList();

        return CollectionModel.of(studentModels,
                linkTo(methodOn(AssignmentController.class).getAssignmentStudents(assignmentId)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<AssignmentDTO>> addAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        EntityModel<AssignmentDTO> assignmentModel = assignmentModelAssembler.toModel(createdAssignment);
        return ResponseEntity
                .created(assignmentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(assignmentModel);

    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<?> removeAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignmentById(assignmentId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assignmentId}")
    public EntityModel<AssignmentDTO> updateAssignment(@PathVariable Long assignmentId, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(assignmentId, assignment);

        return assignmentModelAssembler.toModel(updatedAssignment);
    }
}
