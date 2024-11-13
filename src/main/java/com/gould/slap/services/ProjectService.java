package com.gould.slap.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.gould.slap.domain.CourseEntity;
import com.gould.slap.domain.ProjectEntity;
import com.gould.slap.repositories.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    //Create a new Project
    public ProjectEntity createProject(ProjectEntity projectEntity){
        return projectRepository.save(projectEntity);
    }

    //Save a Project
    public ProjectEntity saveProject(ProjectEntity projectEntity){
        return projectRepository.save(projectEntity);
    }

    //Find All Project
    public List<ProjectEntity> findall(){
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //Find All Projects by Course
    public List<ProjectEntity> findallbyCourse(CourseEntity course){
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
        .filter(project -> course.equals(project.getCourse()))
        .toList();
    }

    // Find a Project by ID
    public Optional<ProjectEntity> findOne(Long id) {
        return projectRepository.findById(id);
    }

    //Find all InstructionDocuments for a specific Project (Has been implemented in the InstructionDocumentsService)

    // Check if a Project exists by ID
    public boolean isExists(Long id) {
        return projectRepository.existsById(id);
    }

    //Partial Update
    public ProjectEntity partialUpdate(Long id, ProjectEntity projectEntity) {
        projectEntity.setProjectId(id);

        return projectRepository.findById(id).map(existingProject -> {
            Optional.ofNullable(projectEntity.getProjectName()).ifPresent(existingProject::setProjectName);
            Optional.ofNullable(projectEntity.getDescription()).ifPresent(existingProject::setDescription);
            Optional.ofNullable(projectEntity.getCourse()).ifPresent(existingProject::setCourse);
            Optional.ofNullable(projectEntity.getCreatedBy()).ifPresent(existingProject::setCreatedBy);
            Optional.ofNullable(projectEntity.getInstructionDocs()).ifPresent(existingProject::setInstructionDocs);

            // Save and return the updated Project
            return projectRepository.save(existingProject);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    //Delete a Project
    public void delete(Long id){
        projectRepository.deleteById(id);
    }
}
