package com.gkritas.privateschoolmanager.assembler;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.model.Course;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CourseModelAssembler implements RepresentationModelAssembler<CourseDTO, EntityModel<CourseDTO>> {
    @Override
    public EntityModel<CourseDTO> toModel(CourseDTO courseDTO) {
        Link selfLink = linkTo(methodOn(CourseController.class).getSingleCourse(courseDTO.getCourseId())).withSelfRel();
        Link allLink = linkTo(methodOn(CourseController.class).getAllCourses()).withRel("courses");

        return EntityModel.of(courseDTO, selfLink, allLink);
    }

}
