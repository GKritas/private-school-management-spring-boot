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

    public Course findCourseById(UUID courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(UUID courseId) {
        courseRepository.deleteById(courseId);
    }

    public Course updateCourse(Course course, UUID courseId) {
        Course updatedCourse = courseRepository.findById(courseId)
                .map(crs -> {
                    crs.setName(course.getName());
                    crs.setDescription(course.getDescription());
                    crs.setStartDate(course.getStartDate());
                    crs.setEndDate(course.getEndDate());
                    crs.setDuration(course.getDuration());
                    crs.setFee(course.getFee());
                    return crs;
                })
                .orElseGet(() -> {
                    course.setCourseId(courseId);
                    return course;
                });

        return courseRepository.save(updatedCourse);
    }
}
