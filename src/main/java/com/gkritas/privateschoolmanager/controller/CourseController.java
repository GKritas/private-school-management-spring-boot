package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.CourseModelAssembler;
import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.dto.AssignmentDTO;
import com.gkritas.privateschoolmanager.dto.CourseDTO;
import com.gkritas.privateschoolmanager.dto.StudentDTO;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.service.CourseService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseModelAssembler courseModelAssembler;
    private final StudentModelAssembler studentModelAssembler;
    private final AssignmentModelAssembler assignmentModelAssembler;

    public CourseController(CourseService courseService,
                            CourseModelAssembler courseModelAssembler,
                            StudentModelAssembler studentModelAssembler,
                            AssignmentModelAssembler assignmentModelAssembler) {
        this.courseService = courseService;
        this.courseModelAssembler = courseModelAssembler;
        this.studentModelAssembler = studentModelAssembler;
        this.assignmentModelAssembler = assignmentModelAssembler;
    }


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CourseDTO>>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        List<EntityModel<CourseDTO>> courseModels = courses.stream().map(courseModelAssembler::toModel).toList();

        return ResponseEntity.ok(CollectionModel.of(courseModels));
    }


    @GetMapping("{id}")
    public ResponseEntity<EntityModel<CourseDTO>> getSingleCourse(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(course);

        return ResponseEntity.ok(courseModel);
    }

    @GetMapping("{id}/students")
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudentsForCourse(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        List<Student> students = course.getStudents();
        List<EntityModel<StudentDTO>> studentModels = students
                .stream()
                .map(studentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(studentModels));
    }

    @GetMapping("{id}/assignments")
    public ResponseEntity<CollectionModel<EntityModel<AssignmentDTO>>> getAssignmentsForCourse(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        List<Assignment> assignments = course.getAssignments();
        List<EntityModel<AssignmentDTO>> assignmentModels = assignments
                .stream()
                .map(assignmentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(assignmentModels));
    }

    @PostMapping
    public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.saveCourse(course);

        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(createdCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(courseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<CourseDTO>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        EntityModel<CourseDTO> courseModel = courseModelAssembler.toModel(updatedCourse);

        return ResponseEntity.created(courseModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(courseModel);
    }
}
