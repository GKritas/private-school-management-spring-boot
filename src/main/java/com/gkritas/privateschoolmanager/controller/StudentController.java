package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.dto.StudentDTO;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.service.StudentService;
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
    private final StudentService studentService;
    private final StudentModelAssembler studentModelAssembler;

    public StudentController(StudentService studentService, StudentModelAssembler studentModelAssembler) {
        this.studentService = studentService;
        this.studentModelAssembler = studentModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        List<EntityModel<StudentDTO>> studentModels = students.stream().map(studentModelAssembler::toModel).toList();

        return ResponseEntity.ok(CollectionModel.of(studentModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getSingleStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(student);

        return ResponseEntity.ok(studentModel);
    }


    @PostMapping
    public ResponseEntity<EntityModel<StudentDTO>> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(createdStudent);
        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(updatedStudent);

        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }
}
