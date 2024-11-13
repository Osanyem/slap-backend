package com.gould.slap.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gould.slap.domain.ProjectEntity;
import com.gould.slap.domain.CourseEntity;
import com.gould.slap.services.ProjectService;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    //Create a new Project
    @PostMapping(path = "/projects")
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectEntity projectEntity) {
        ProjectEntity createdProject = projectService.createProject(projectEntity);
        
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    //Get all Projects from a specific course
    @GetMapping(path = "/projects/course/{course}")
    public List<ProjectEntity> listCourseProjects(@PathVariable("course") CourseEntity course) {
        return projectService.findAllByCourse(course);
    }

    //Get a Project by ID
    @GetMapping(path = "/projects/{id}")
    public Optional<ProjectEntity> listProjectByID(@PathVariable("id") Long id) {
        return projectService.findOne(id);
    }

    //Edit a Project (Partial Update)
    @PatchMapping(path = "/projects/{id}")
    public ResponseEntity<ProjectEntity> editProject(@PathVariable("id") Long id, @RequestBody ProjectEntity projectEntity){
        if(!projectService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProjectEntity updatedProject = projectService.partialUpdate(id,projectEntity);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    //Delete a project by ID
    @DeleteMapping(path = "/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id){
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
