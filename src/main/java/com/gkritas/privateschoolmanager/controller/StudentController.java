package com.gkritas.privateschoolmanager.controller;

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
    public CollectionModel<EntityModel<Student>> getAllStudents() {
        List<EntityModel<Student>> students = studentService.findAllStudents().stream()
                .map(studentModelAssembler::toModel).toList();

        return CollectionModel.of(students, linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
    }

    @GetMapping("/{studentId}")
    public EntityModel<Student> getSingleStudent(@PathVariable UUID studentId) {
        Student student = studentService.findStudentById(studentId);

        return studentModelAssembler.toModel(student);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        EntityModel<Student> entityModel = studentModelAssembler.toModel(studentService.createStudent(student));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable UUID studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable UUID studentId, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(studentId, student);

        EntityModel<Student> entityModel = studentModelAssembler.toModel(updatedStudent);


        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
