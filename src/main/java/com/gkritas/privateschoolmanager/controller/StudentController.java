package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.domain.Course;
import com.gkritas.privateschoolmanager.domain.Student;
import com.gkritas.privateschoolmanager.modelAssembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.service.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentModelAssembler studentModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<StudentDTO>> getAllStudents() {
        List<Student> students =studentService.findAllStudents();
        List<EntityModel<StudentDTO>> studentModels = students.stream()
                .map(studentModelAssembler::toModel)
                .toList();

        return CollectionModel.of(studentModels,
                linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
    }

    @GetMapping("/{studentId}")
    public EntityModel<StudentDTO> getSingleStudent(@PathVariable Long studentId) {
        Student student = studentService.findStudentById(studentId);
        return studentModelAssembler.toModel(student);
    }

    @GetMapping("/{studentId}/courses")
    public CollectionModel<EntityModel<CourseDTO>>getStudentCourses(@PathVariable Long studentId) {
        Student student = studentService.findStudentById(studentId);
        List<Course> courses = student.getCourses();
        List<EntityModel<CourseDTO>> courseModels = courses.stream()
                .map(course -> EntityModel.of(new CourseDTO(course)))
                .toList();

        return CollectionModel.of(courseModels,
                linkTo(methodOn(StudentController.class).getStudentCourses(studentId)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<StudentDTO>> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(createdStudent);
        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public EntityModel<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(studentId, student);
        return studentModelAssembler.toModel(updatedStudent);
    }
}
