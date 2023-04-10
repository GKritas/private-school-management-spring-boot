package com.gkritas.privateschoolmanager.assembler;

import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.controller.StudentController;
import com.gkritas.privateschoolmanager.model.Student;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class StudentModelAssembler implements RepresentationModelAssembler<StudentDTO, EntityModel<StudentDTO>> {

    @Override
    public EntityModel<StudentDTO> toModel(StudentDTO studentDTO) {
        Link selfLink = linkTo(methodOn(StudentController.class).getSingleStudent(studentDTO.getStudentId())).withSelfRel();
        Link allLink = linkTo(methodOn(StudentController.class).getAllStudents()).withRel("students");
        return EntityModel.of(studentDTO, selfLink, allLink);
    }
}
