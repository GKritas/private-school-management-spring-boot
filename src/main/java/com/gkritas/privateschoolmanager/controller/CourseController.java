package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
import com.gkritas.privateschoolmanager.assembler.CourseModelAssembler;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseModelAssembler courseModelAssembler;


    @GetMapping
    public CollectionModel<EntityModel<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.findAllCourses();

        List<EntityModel<CourseDTO>> courseModels = courses.stream()
                .map(courseModelAssembler::toModel)
                .toList();

        return CollectionModel.of(courseModels,
                linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel());
    }


    @GetMapping("/{courseId}")
    public EntityModel<CourseDTO> getSingleCourse(@PathVariable Long courseId) {

        CourseDTO course = courseService.findCourseById(courseId);

        return courseModelAssembler.toModel(course);
    }

    @PostMapping
    public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody CourseDTO course) {
        CourseDTO createdCourse = courseService.createCourse(course);

        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(createdCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(courseModel);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> removeCourse(@PathVariable Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}")
    public EntityModel<CourseDTO> updateCourse(@RequestBody CourseDTO course,@PathVariable Long courseId) {
        CourseDTO updatedCourse = courseService.updateCourse(course, courseId);
        return courseModelAssembler.toModel(updatedCourse);
    }
}
