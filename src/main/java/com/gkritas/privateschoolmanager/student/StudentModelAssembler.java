package com.gkritas.privateschoolmanager.student;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

    @Override
    public EntityModel<Student> toModel(Student student) {
        return EntityModel.of(student,
                linkTo(methodOn(StudentController.class).getSingleStudent(student.getStudentId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).getAllStudents()).withRel("students"));
    }
}
