package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.CourseModelAssembler;
import com.gkritas.privateschoolmanager.dto.CourseDTO;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.service.CourseService;
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
    private final CourseService courseService;
    private final CourseModelAssembler courseModelAssembler;

    public CourseController(CourseService courseService, CourseModelAssembler courseModelAssembler) {
        this.courseService = courseService;
        this.courseModelAssembler = courseModelAssembler;
    }


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CourseDTO>>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        List<EntityModel<CourseDTO>> courseModels = courses.stream().map(courseModelAssembler::toModel).toList();

        return ResponseEntity.ok(CollectionModel.of(courseModels));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CourseDTO>> getSingleCourse(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(course);

        return ResponseEntity.ok(courseModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.saveCourse(course);

        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(createdCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(courseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CourseDTO>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(updatedCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(courseModel);
    }
}
