package com.gould.slap.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gould.slap.domain.UserEntity;
import com.gould.slap.domain.CourseEntity;
import com.gould.slap.domain.SlapEntity;
import com.gould.slap.services.SlapService;

@RestController
public class SlapController {
    private final SlapService slapService;

    public SlapController(SlapService slapService){
        this.slapService = slapService;
    }

    //Create a new Slap
    @PostMapping(path = "/slaps")
    public ResponseEntity<SlapEntity> createSlap(@RequestBody SlapEntity slapEntity) {
        SlapEntity createdSlap = slapService.createSlap(slapEntity);
        
        return new ResponseEntity<>(createdSlap, HttpStatus.CREATED);
    }

    //Get all Slaps From a specific creator
    @GetMapping(path = "/slaps/creator/{creator}")
    public List<SlapEntity> listCourseSlaps(@PathVariable("creator") UserEntity creator) {
        return slapService.findAllByCreator(creator);
    }

    //Get all Slaps From a specific course
    @GetMapping(path = "/slaps/course/{course}")
    public List<SlapEntity> listCourseSlaps(@PathVariable("course") CourseEntity course) {
        return slapService.findAllByCourse(course);
    }

    //Get a Slap by id
    @GetMapping(path = "/slaps/{id}")
    public Optional<SlapEntity> listSlapByID(@PathVariable("id") Long id) {
        return slapService.findOne(id);
    }

    //Edit a Slap (Partial Update)
    @PatchMapping(path = "/slaps/{id}")
    public ResponseEntity<SlapEntity> editSlap(@PathVariable("id") Long id, @RequestBody SlapEntity slapEntity){
        if(!slapService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SlapEntity updatedSlap = slapService.partialUpdate(id,slapEntity);
        return new ResponseEntity<>(updatedSlap, HttpStatus.OK);
    }

    //Delete a Slap by ID
    @DeleteMapping(path = "/slaps/{id}")
    public ResponseEntity<Void> deleteSlap(@PathVariable("id") Long id){
        slapService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
