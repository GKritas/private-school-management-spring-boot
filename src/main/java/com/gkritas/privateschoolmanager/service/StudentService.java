package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.DTO.StudentDTO;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.exception.StudentNotFoundException;
import com.gkritas.privateschoolmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentDTO::new).collect(Collectors.toList());
    }


    public StudentDTO findStudentById(Long studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(()->
                        new StudentNotFoundException("Student not found with id: " + studentId));

        return new StudentDTO(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setGender(studentDTO.getGender());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEnrollmentDate(studentDTO.getEnrollmentDate());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        studentRepository.save(student);
        studentDTO.setStudentId(student.getStudentId());
        return studentDTO;

    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public StudentDTO updateStudent(Long studentId, StudentDTO student) {
        Student updatedStudent = studentRepository
                .findById(studentId)
                .orElseThrow(()->
                        new StudentNotFoundException("Student not found with id: " + studentId));

        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setPhoneNumber(student.getPhoneNumber());
        updatedStudent.setDateOfBirth(student.getDateOfBirth());
        updatedStudent.setGender(student.getGender());
        updatedStudent.setEnrollmentDate(student.getEnrollmentDate());

        return new StudentDTO(studentRepository.save(updatedStudent));
    }
}
