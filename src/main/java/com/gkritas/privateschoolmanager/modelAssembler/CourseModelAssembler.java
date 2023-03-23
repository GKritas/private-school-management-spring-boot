package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.domain.Course;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<CourseDTO>> {
    @Override
    public EntityModel<CourseDTO> toModel(Course course) {
        CourseDTO courseDTO = new CourseDTO(course);
        Link selfLink = linkTo(methodOn(CourseController.class).getSingleCourse(course.getCourseId())).withSelfRel();

        return EntityModel.of(courseDTO, selfLink);
    }
}
