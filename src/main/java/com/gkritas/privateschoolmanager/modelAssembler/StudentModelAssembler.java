package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.controller.StudentController;
import com.gkritas.privateschoolmanager.domain.Student;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<StudentDTO>> {

    @Override
    public EntityModel<StudentDTO> toModel(Student student) {
        StudentDTO studentDTO = new StudentDTO(student);
        Link selfLink = linkTo(methodOn(StudentController.class).getSingleStudent(student.getStudentId())).withSelfRel();
        Link coursesLink = linkTo(methodOn(StudentController.class).getStudentCourses(student.getStudentId())).withRel("courses");
        return EntityModel.of(studentDTO, selfLink, coursesLink);
    }
}
