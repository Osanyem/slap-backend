package com.gould.slap.services;

import com.gould.slap.domain.CourseEntity;
import com.gould.slap.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Create a new course
    public CourseEntity createCourse(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    // Save a course (full or partial update)
    public CourseEntity save(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    // Find all courses
    public List<CourseEntity> findAll() {
        return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Find a course by ID
    public Optional<CourseEntity> findOne(Long id) {
        return courseRepository.findById(id);
    }

    // Check if a course exists by ID
    public boolean isExists(Long id) {
        return courseRepository.existsById(id);
    }

    // Partial update for a course
    public CourseEntity partialUpdate(Long id, CourseEntity courseEntity) {
        courseEntity.setCourseId(id);

        return courseRepository.findById(id).map(existingCourse -> {
            Optional.ofNullable(courseEntity.getCourseName()).ifPresent(existingCourse::setCourseName);
            Optional.ofNullable(courseEntity.getCourseCode()).ifPresent(existingCourse::setCourseCode);
            Optional.ofNullable(courseEntity.getEnrollmentDeadline()).ifPresent(existingCourse::setEnrollmentDeadline);
            Optional.ofNullable(courseEntity.getStartDate()).ifPresent(existingCourse::setStartDate);
            Optional.ofNullable(courseEntity.getEndDate()).ifPresent(existingCourse::setEndDate);
            Optional.ofNullable(courseEntity.getDescription()).ifPresent(existingCourse::setDescription);
            Optional.ofNullable(courseEntity.getInstructor()).ifPresent(existingCourse::setInstructor);
            Optional.ofNullable(courseEntity.getStudents()).ifPresent(existingCourse::setStudents);

            // Save and return the updated course
            return courseRepository.save(existingCourse);
        }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    // Delete a course by ID
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
