package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.exception.StudentNotFoundException;
import com.gkritas.privateschoolmanager.model.Student;
import com.gkritas.privateschoolmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public Student findStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(()->
                        new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        Student deletedStudent = findStudentById(id);
        studentRepository.delete(deletedStudent);
    }

    public Student updateStudent(Long id, Student student) {
        Student updatedStudent = findStudentById(id);

        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setPhoneNumber(student.getPhoneNumber());
        updatedStudent.setDateOfBirth(student.getDateOfBirth());
        updatedStudent.setGender(student.getGender());
        updatedStudent.setEnrollmentDate(student.getEnrollmentDate());

        return studentRepository.save(updatedStudent);
    }
}
