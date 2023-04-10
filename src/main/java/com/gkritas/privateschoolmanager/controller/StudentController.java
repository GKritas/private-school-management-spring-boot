package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
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
    public CollectionModel<EntityModel<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.findAllStudents();
        List<EntityModel<StudentDTO>> studentModels = students.stream().map(studentModelAssembler::toModel).toList();

        return CollectionModel.of(studentModels, linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
    }

    @GetMapping("/{studentId}")
    public EntityModel<StudentDTO> getSingleStudent(@PathVariable Long studentId) {
        StudentDTO student = studentService.findStudentById(studentId);
        return studentModelAssembler.toModel(student);
    }


    @PostMapping
    public ResponseEntity<EntityModel<StudentDTO>> addStudent(@RequestBody StudentDTO student) {
        StudentDTO createdStudent = studentService.createStudent(student);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(createdStudent);
        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public EntityModel<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO student) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, student);
        return studentModelAssembler.toModel(updatedStudent);
    }
}
