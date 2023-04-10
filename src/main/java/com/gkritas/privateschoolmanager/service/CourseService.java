package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.DTO.CourseDTO;
import com.gkritas.privateschoolmanager.model.Course;
import com.gkritas.privateschoolmanager.exception.CourseNotFoundException;
import com.gkritas.privateschoolmanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    public CourseDTO findCourseById(Long courseId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));

        return new CourseDTO(course);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setStartDate(courseDTO.getStartDate());
        course.setEndDate(courseDTO.getEndDate());
        course.setDuration(courseDTO.getDuration());
        course.setFee(courseDTO.getFee());
        courseRepository.save(course);
        courseDTO.setCourseId(course.getCourseId());
        return courseDTO;
    }

    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO, Long courseId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + courseId));
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setStartDate(courseDTO.getStartDate());
        course.setEndDate(courseDTO.getEndDate());
        course.setDuration(courseDTO.getDuration());
        course.setFee(courseDTO.getFee());
        courseRepository.save(course);
        return courseDTO;

    }
}
