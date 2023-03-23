package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.domain.Course;
import com.gkritas.privateschoolmanager.exception.CourseNotFoundException;
import com.gkritas.privateschoolmanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long courseId) {
        return courseRepository
                .findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));


    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long courseId) {
        Course deletedCourse = findCourseById(courseId);
        courseRepository.delete(deletedCourse);
    }

    public Course updateCourse(Course course, Long courseId) {
        Course updatedCourse = findCourseById(courseId);

        updatedCourse.setName(course.getName());
        updatedCourse.setDescription(course.getDescription());
        updatedCourse.setStartDate(course.getStartDate());
        updatedCourse.setEndDate(course.getEndDate());
        updatedCourse.setDuration(course.getDuration());
        updatedCourse.setFee(course.getFee());

        return courseRepository.save(updatedCourse);
    }
}
