package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.domain.Course;
import com.gkritas.privateschoolmanager.modelAssembler.CourseModelAssembler;
import com.gkritas.privateschoolmanager.service.CourseService;
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
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseModelAssembler courseModelAssembler;


    @GetMapping
    public CollectionModel<EntityModel<Course>> getAllCourses() {

        List<EntityModel<Course>> courses = courseService.findAllCourses().stream()
                .map(courseModelAssembler::toModel).toList();

        return CollectionModel.of(courses, linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel());
    }


    @GetMapping("/{courseId}")
    public EntityModel<Course> getSingleCourse(@PathVariable UUID courseId) {

        Course course = courseService.findCourseById(courseId);

        return courseModelAssembler.toModel(course);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {

        EntityModel<Course> entityModel = courseModelAssembler.toModel(courseService.createCourse(course));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> removeCourse(@PathVariable UUID courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody Course course,@PathVariable UUID courseId) {
        Course updatedCourse = courseService.updateCourse(course, courseId);

        EntityModel<Course> entityModel = courseModelAssembler.toModel(updatedCourse);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
