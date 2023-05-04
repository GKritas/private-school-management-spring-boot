package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.assembler.AssignmentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.CourseModelAssembler;
import com.gkritas.privateschoolmanager.assembler.StudentModelAssembler;
import com.gkritas.privateschoolmanager.assembler.TrainerModelAssembler;
import com.gkritas.privateschoolmanager.dto.AssignmentDTO;
import com.gkritas.privateschoolmanager.dto.CourseDTO;
import com.gkritas.privateschoolmanager.dto.StudentDTO;
import com.gkritas.privateschoolmanager.dto.TrainerDTO;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentModelAssembler studentModelAssembler;
    private final CourseModelAssembler courseModelAssembler;
    private final TrainerModelAssembler trainerModelAssembler;
    private final AssignmentModelAssembler assignmentModelAssembler;

    public StudentController(StudentService studentService,
                             StudentModelAssembler studentModelAssembler,
                             CourseModelAssembler courseModelAssembler,
                             TrainerModelAssembler trainerModelAssembler,
                             AssignmentModelAssembler assignmentModelAssembler) {
        this.studentService = studentService;
        this.studentModelAssembler = studentModelAssembler;
        this.courseModelAssembler = courseModelAssembler;
        this.trainerModelAssembler = trainerModelAssembler;
        this.assignmentModelAssembler = assignmentModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        List<EntityModel<StudentDTO>> studentModels = students.stream().map(studentModelAssembler::toModel).toList();

        return ResponseEntity.ok(CollectionModel.of(studentModels));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getSingleStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(student);

        return ResponseEntity.ok(studentModel);
    }

    @GetMapping("{id}/courses")
    public ResponseEntity<CollectionModel<EntityModel<CourseDTO>>> getCoursesForStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        List<Course> courses = student.getCourses();
        List<EntityModel<CourseDTO>> courseModels = courses
                .stream()
                .map(courseModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(courseModels));
    }

    @GetMapping("{id}/trainers")
    public ResponseEntity<CollectionModel<EntityModel<TrainerDTO>>> getTrainersForStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        List<Trainer> trainers = student.getTrainers();
        List<EntityModel<TrainerDTO>> trainerModels = trainers
                .stream()
                .map(trainerModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(trainerModels));
    }

    @GetMapping("{id}/assignments")
    public ResponseEntity<CollectionModel<EntityModel<AssignmentDTO>>> getAssignmentsForStudent(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        List<Assignment> assignments = student.getAssignments();
        List<EntityModel<AssignmentDTO>> assignmentModels = assignments
                .stream()
                .map(assignmentModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(assignmentModels));
    }


    @PostMapping
    public ResponseEntity<EntityModel<StudentDTO>> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        EntityModel<StudentDTO> studentModel = studentModelAssembler.toModel(createdStudent);
        return ResponseEntity.created(studentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(studentModel);
    }

    @DeleteMapping("{id}")
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
