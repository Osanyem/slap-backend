package com.gould.slap.controllers;

import com.gould.slap.domain.CourseEntity;
import com.gould.slap.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Create a new course
    @PostMapping(path = "/courses")
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity courseEntity) {
        CourseEntity createdCourse = courseService.createCourse(courseEntity);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    // Get all courses
    @GetMapping(path = "/courses")
    public List<CourseEntity> listCourses() {
        List<CourseEntity> courses = courseService.findAll();
        return courses.stream().collect(Collectors.toList());
    }

    // Get a single course by ID
    @GetMapping(path = "/courses/{id}")
    public ResponseEntity<CourseEntity> getCourse(@PathVariable("id") Long id) {
        Optional<CourseEntity> course = courseService.findOne(id);
        return course.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Full update (replace) a course by ID
    @PutMapping(path = "/courses/{id}")
    public ResponseEntity<CourseEntity> fullUpdateCourse(@PathVariable("id") Long id, @RequestBody CourseEntity courseEntity) {
        if (!courseService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseEntity.setCourseId(id);
        CourseEntity updatedCourse = courseService.save(courseEntity);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    // Partial update a course by ID
    @PatchMapping(path = "/courses/{id}")
    public ResponseEntity<CourseEntity> partialUpdateCourse(@PathVariable("id") Long id, @RequestBody CourseEntity courseEntity) {
        if (!courseService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CourseEntity updatedCourse = courseService.partialUpdate(id, courseEntity);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    // Delete a course by ID
    @DeleteMapping(path = "/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
