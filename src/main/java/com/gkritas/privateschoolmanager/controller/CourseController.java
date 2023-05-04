package com.gkritas.privateschoolmanager.controller;

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
    public CollectionModel<EntityModel<Course>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();

        List<EntityModel<Course>> courseModels = courses.stream()
                .map(courseModelAssembler::toModel)
                .toList();

        return CollectionModel.of(courseModels,
                linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel());
    }


    @GetMapping("/{id}")
    public EntityModel<Course> getSingleCourse(@PathVariable Long id) {

        Course course = courseService.findCourseById(id);

        return courseModelAssembler.toModel(course);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Course>> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.saveCourse(course);

        EntityModel<Course> courseModel = courseModelAssembler.toModel(createdCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(courseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public EntityModel<Course> updateCourse(@RequestBody Course course,@PathVariable Long id) {
        Course updatedCourse = courseService.updateCourse(course, id);
        return courseModelAssembler.toModel(updatedCourse);
    }
}
