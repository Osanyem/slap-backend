package com.gould.slap.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.gould.slap.domain.CourseEntity;
import com.gould.slap.domain.SubmissionEntity;
import com.gould.slap.domain.UserEntity;
import com.gould.slap.repositories.SubmissionRepository;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository){
        this.submissionRepository = submissionRepository;
    }

    //Create a new Submission
    public SubmissionEntity createSubmission(SubmissionEntity submissionEntity){
        return submissionRepository.save(submissionEntity);
    }

    //Save a Submission
    public SubmissionEntity saveSubmission(SubmissionEntity submissionEntity){
        return submissionRepository.save(submissionEntity);
    }

    //Find All Submission
    public List<SubmissionEntity> findAll(){
        return StreamSupport.stream(submissionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //Find All Submissions by Student
    public List<SubmissionEntity> findAllByStudent(UserEntity student){
        return StreamSupport.stream(submissionRepository.findAll().spliterator(), false)
        .filter(submission -> student.equals(submission.getStudent()))
        .toList();
    }
    
    //Find All Submissions by Course
    public List<SubmissionEntity> findAllByCourse(CourseEntity course){
        return StreamSupport.stream(submissionRepository.findAll().spliterator(), false)
        .filter(submission -> course.equals(submission.getCourse()))
        .toList();
    }

    // Find a Submission by ID
    public Optional<SubmissionEntity> findOne(Long id) {
        return submissionRepository.findById(id);
    }

    // Check if a Submission exists by ID
    public boolean isExists(Long id) {
        return submissionRepository.existsById(id);
    }

    //Partial Update
    public SubmissionEntity partialUpdate(Long id, SubmissionEntity submissionEntity) {
        submissionEntity.setSubmissionId(id);

        return submissionRepository.findById(id).map(existingSubmission -> {
            Optional.ofNullable(submissionEntity.getStudent()).ifPresent(existingSubmission::setStudent);
            Optional.ofNullable(submissionEntity.getCourse()).ifPresent(existingSubmission::setCourse);
            Optional.ofNullable(submissionEntity.getDescription()).ifPresent(existingSubmission::setDescription);
            
            // Save and return the updated Submission
            return submissionRepository.save(existingSubmission);
        }).orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    //Delete a Submission
    public void delete(Long id){
        submissionRepository.deleteById(id);
    }
}
