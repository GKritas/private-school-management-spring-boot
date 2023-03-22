package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.controller.AssignmentController;
import com.gkritas.privateschoolmanager.domain.Assignment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AssignmentModelAssembler implements RepresentationModelAssembler<Assignment, EntityModel<Assignment>> {
    @Override
    public EntityModel<Assignment> toModel(Assignment assignment) {
        return EntityModel.of(assignment,
                WebMvcLinkBuilder.linkTo(methodOn(AssignmentController.class).getSingleAssignment(assignment.getAssignmentId())).withSelfRel(),
                linkTo(methodOn(AssignmentController.class).getAllAssignments()).withRel("assignments"));
    }
}
