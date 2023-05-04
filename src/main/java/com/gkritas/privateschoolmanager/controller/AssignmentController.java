package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.dto.AssignmentDTO;
import com.gkritas.privateschoolmanager.dto.StudentDTO;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.service.AssignmentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    private final AssignmentModelAssembler assignmentModelAssembler;
    private final StudentModelAssembler studentModelAssembler;

    public AssignmentController(AssignmentService assignmentService,
                                AssignmentModelAssembler assignmentModelAssembler,
                                StudentModelAssembler studentModelAssembler) {
        this.assignmentService = assignmentService;
        this.assignmentModelAssembler = assignmentModelAssembler;
        this.studentModelAssembler = studentModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AssignmentDTO>>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.findAllAssignment();
        List<EntityModel<AssignmentDTO>> assignmentModels = assignments.stream()
                .map(assignmentModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(assignmentModels));
    }


    @GetMapping("{id}")
    public ResponseEntity<EntityModel<AssignmentDTO>> getSingleAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findAssignmentById(id);
        EntityModel<AssignmentDTO> assignmentModel = assignmentModelAssembler.toModel(assignment);
        return ResponseEntity.ok(assignmentModel);
    }

    @GetMapping("{id}/students")
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudentsForAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findAssignmentById(id);
        List<Student> students = assignment.getStudents();
        List<EntityModel<StudentDTO>> studentModels = students
                .stream()
                .map(studentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(studentModels));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AssignmentDTO>> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.saveAssignment(assignment);
        EntityModel<AssignmentDTO> assignmentModel = assignmentModelAssembler.toModel(createdAssignment);

        return ResponseEntity
                .created(assignmentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(assignmentModel);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<AssignmentDTO>> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(id, assignment);
        EntityModel<AssignmentDTO> assignmentModel = assignmentModelAssembler.toModel(updatedAssignment);

        return ResponseEntity.created(assignmentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(assignmentModel);
    }
}
