package com.gkritas.privateschoolmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public Optional<Student> findStudentById(UUID studentId) {
        return studentRepository.findById(studentId);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student updateStudent(UUID studentId, Student student) {
        Student updatedStudent = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + studentId));

        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setGender(student.getGender());
        updatedStudent.setDateOfBirth(student.getDateOfBirth());
        updatedStudent.setEnrollmentDate(student.getEnrollmentDate());
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setGuardianFirstName(student.getGuardianFirstName());
        updatedStudent.setGuardianLastName(student.getGuardianLastName());
        updatedStudent.setGuardianEmail(student.getGuardianEmail());
        updatedStudent.setGuardianPhoneNumber(student.getGuardianPhoneNumber());

        return studentRepository.save(updatedStudent);
    }
}
