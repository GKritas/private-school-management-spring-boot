package com.gkritas.privateschoolmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Optional<Student>> getSingleStudent(@PathVariable UUID studentId) {
        return new ResponseEntity<>(studentService.findStudentById(studentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeStudent(@PathVariable UUID studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID studentId, @RequestBody Student student) {
        Student updatedStudent= studentService.updateStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }
}
