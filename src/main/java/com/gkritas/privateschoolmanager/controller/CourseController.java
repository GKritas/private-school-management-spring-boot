package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
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
    public CollectionModel<EntityModel<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();

        List<EntityModel<CourseDTO>> courseModels = courses.stream()
                .map(courseModelAssembler::toModel)
                .toList();

        return CollectionModel.of(courseModels,
                linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel());
    }


    @GetMapping("/{courseId}")
    public EntityModel<CourseDTO> getSingleCourse(@PathVariable Long courseId) {

        Course course = courseService.findCourseById(courseId);

        return courseModelAssembler.toModel(course);
    }

    @PostMapping
    public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);

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
    public EntityModel<CourseDTO> updateCourse(@RequestBody Course course,@PathVariable Long courseId) {
        Course updatedCourse = courseService.updateCourse(course, courseId);
        return courseModelAssembler.toModel(updatedCourse);
    }
}
