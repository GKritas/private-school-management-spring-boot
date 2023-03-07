package com.gkritas.privateschoolmanager.assignment;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AssignmentModelAssembler implements RepresentationModelAssembler<Assignment, EntityModel<Assignment>> {
    @Override
    public EntityModel<Assignment> toModel(Assignment assignment) {
        return EntityModel.of(assignment,
                linkTo(methodOn(AssignmentController.class).getSingleAssignment(assignment.getAssignmentId())).withSelfRel(),
                linkTo(methodOn(AssignmentController.class).getAllAssignments()).withRel("assignments"));
    }
}
