package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.DTO.AssignmentDTO;
import com.gkritas.privateschoolmanager.controller.AssignmentController;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.domain.Assignment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AssignmentModelAssembler implements RepresentationModelAssembler<Assignment, EntityModel<AssignmentDTO>> {
    @Override
    public EntityModel<AssignmentDTO> toModel(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO(assignment);
        Link selfLink = linkTo(methodOn(AssignmentController.class).getSingleAssignment(assignment.getAssignmentId())).withSelfRel();
        Link courseLink = linkTo(methodOn(CourseController.class).getSingleCourse(assignment.getCourse().getCourseId())).withRel("course");
        Link studentsLink = linkTo(methodOn(AssignmentController.class).getAssignmentStudents(assignment.getAssignmentId())).withRel("students");
        return EntityModel.of(assignmentDTO, selfLink, courseLink, studentsLink);
    }
}
