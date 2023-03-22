package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.domain.Student;
import com.gkritas.privateschoolmanager.exception.StudentNotFoundException;
import com.gkritas.privateschoolmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public Student findStudentById(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student not found with id " + studentId));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student updateStudent(UUID studentId, Student student) {
        Student updatedStudent = studentRepository.findById(studentId)
                .map(std -> {
                    std.setFirstName(student.getFirstName());
                    std.setLastName(student.getLastName());
                    std.setGender(student.getGender());
                    std.setEmail(student.getEmail());
                    std.setAddress(student.getAddress());
                    std.setDateOfBirth(student.getDateOfBirth());
                    std.setEnrollmentDate(student.getEnrollmentDate());
                    std.setGuardianLastName(student.getGuardianLastName());
                    std.setGuardianFirstName(student.getGuardianFirstName());
                    std.setGuardianEmail(student.getGuardianEmail());
                    std.setGuardianPhoneNumber(student.getGuardianPhoneNumber());
                    std.setPhoneNumber(student.getPhoneNumber());
                    return std;
                })
                .orElseGet(()-> {
                    student.setStudentId(studentId);
                    return student;
                });

        return studentRepository.save(updatedStudent);
    }
}
