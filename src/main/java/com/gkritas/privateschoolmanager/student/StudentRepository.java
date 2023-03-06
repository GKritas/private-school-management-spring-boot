package com.gkritas.privateschoolmanager.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findByUsername(String username);
}
