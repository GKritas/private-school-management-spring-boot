package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.domain.Student;
import com.gkritas.privateschoolmanager.exception.StudentNotFoundException;
import com.gkritas.privateschoolmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student not found with id " + studentId));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        Student deletedStudent = findStudentById(studentId);
        studentRepository.delete(deletedStudent);
    }

    public Student updateStudent(Long studentId, Student student) {
        Student updatedStudent = findStudentById(studentId);

        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setPhoneNumber(student.getPhoneNumber());
        updatedStudent.setDateOfBirth(student.getDateOfBirth());
        updatedStudent.setGender(student.getGender());
        updatedStudent.setEnrollmentDate(student.getEnrollmentDate());
        updatedStudent.setCourses(student.getCourses());

        return studentRepository.save(updatedStudent);
    }
}
