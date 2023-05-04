package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.service.StudentService;
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
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentModelAssembler studentModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        List<EntityModel<Student>> studentModels = students.stream().map(studentModelAssembler::toModel).toList();

        return CollectionModel.of(studentModels,
                linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Student> getSingleStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        return studentModelAssembler.toModel(student);
    }


    @PostMapping
    public ResponseEntity<EntityModel<Student>> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        EntityModel<Student> studentModel = studentModelAssembler.toModel(createdStudent);
        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public EntityModel<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return studentModelAssembler.toModel(updatedStudent);
    }
}
