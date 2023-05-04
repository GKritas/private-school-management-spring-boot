package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.exception.CourseNotFoundException;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long id) {
        return courseRepository
                .findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));

    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long id) {
        Course deletedCourse = findCourseById(id);
        courseRepository.delete(deletedCourse);
    }

    public Course updateCourse(Course course, Long id) {
        Course updatedCourse = findCourseById(id);
        updatedCourse.setName(course.getName());
        updatedCourse.setDescription(course.getDescription());
        updatedCourse.setStartDate(course.getStartDate());
        updatedCourse.setEndDate(course.getEndDate());
        updatedCourse.setDuration(course.getDuration());
        updatedCourse.setFee(course.getFee());

        return courseRepository.save(updatedCourse);
    }
}
