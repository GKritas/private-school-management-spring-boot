package com.gkritas.privateschoolmanager.repository;

import com.gkritas.privateschoolmanager.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
