package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.domain.Course;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>> {
    @Override
    public EntityModel<Course> toModel(Course course) {
        return EntityModel.of(course,
                WebMvcLinkBuilder.linkTo(methodOn(CourseController.class).getSingleCourse(course.getCourseId())).withSelfRel(),
                linkTo(methodOn(CourseController.class).getAllCourses()).withRel("courses"));
    }
}
