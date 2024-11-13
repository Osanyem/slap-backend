package com.gould.slap.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gould.slap.domain.CourseEntity;
import com.gould.slap.domain.SubmissionEntity;
import com.gould.slap.domain.UserEntity;
import com.gould.slap.services.SubmissionService;

@RestController
public class SubmissonController {
    private final SubmissionService submissionService;

    public SubmissonController(SubmissionService submissionService){
        this.submissionService = submissionService;
    }

    //Create a new Submission
    @PostMapping(path = "/submissions")
    public ResponseEntity<SubmissionEntity> createSubmission(@RequestBody SubmissionEntity submissionEntity) {
        SubmissionEntity createdSubmission = submissionService.createSubmission(submissionEntity);
        
        return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
    }

    //Get all Submissions from a specific student
    @GetMapping(path = "/submissions/student/{student}")
    public List<SubmissionEntity> listCourseSubmissions(@PathVariable("course") UserEntity student) {
        return submissionService.findAllByStudent(student);
    }

    //Get all Submissions from a specific course
    @GetMapping(path = "/submissions/course/{course}")
    public List<SubmissionEntity> listCourseSubmissions(@PathVariable("course") CourseEntity course) {
        return submissionService.findAllByCourse(course);
    }

    //Get a Submission by ID
    @GetMapping(path = "/submissions/{id}")
    public Optional<SubmissionEntity> listSubmissionByID(@PathVariable("id") Long id) {
        return submissionService.findOne(id);
    }

    //Edit a Submission (Partial Update)
    @PatchMapping(path = "/submissions/{id}")
    public ResponseEntity<SubmissionEntity> editSubmission(@PathVariable("id") Long id, @RequestBody SubmissionEntity submissionEntity){
        if(!submissionService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SubmissionEntity updatedSubmission = submissionService.partialUpdate(id,submissionEntity);
        return new ResponseEntity<>(updatedSubmission, HttpStatus.OK);
    }

    //Delete a Submission by ID
    @DeleteMapping(path = "/submissions/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable("id") Long id){
        submissionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
