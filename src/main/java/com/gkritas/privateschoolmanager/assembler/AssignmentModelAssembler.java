package com.gkritas.privateschoolmanager.assembler;

import com.gkritas.privateschoolmanager.DTO.AssignmentDTO;
import com.gkritas.privateschoolmanager.controller.AssignmentController;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.model.Assignment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AssignmentModelAssembler implements RepresentationModelAssembler<AssignmentDTO, EntityModel<AssignmentDTO>> {
    @Override
    public EntityModel<AssignmentDTO> toModel(AssignmentDTO assignment) {
        Link selfLink = linkTo(methodOn(AssignmentController.class).getSingleAssignment(assignment.getAssignmentId())).withSelfRel();
        Link allLink = linkTo(methodOn(AssignmentController.class).getAllAssignments()).withRel("assignments");
        return EntityModel.of(assignment, selfLink);
    }
}
