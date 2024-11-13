package com.gould.slap.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.gould.slap.domain.UserEntity;
import com.gould.slap.domain.CourseEntity;
import com.gould.slap.domain.SlapEntity;
import com.gould.slap.repositories.SlapRepository;

@Service
public class SlapService {
    
    private final SlapRepository slapRepository;

    public SlapService(SlapRepository slapRepository){
        this.slapRepository = slapRepository;
    }

    //Create a new Slap
    public SlapEntity createSlap(SlapEntity slapEntity){
        return slapRepository.save(slapEntity);
    }

    //Save a Slap
    public SlapEntity saveSlap(SlapEntity slapEntity){
        return slapRepository.save(slapEntity);
    }

    //Find All Slap
    public List<SlapEntity> findAll(){
        return StreamSupport.stream(slapRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //Find All Slaps by Creator
    public List<SlapEntity> findAllByCreator(UserEntity creator){
        return StreamSupport.stream(slapRepository.findAll().spliterator(), false)
        .filter(slap -> creator.equals(slap.getCreator()))
        .toList();
    }

    //Find All Slaps by Course
    public List<SlapEntity> findAllByCourse(CourseEntity course){
        return StreamSupport.stream(slapRepository.findAll().spliterator(), false)
        .filter(slap -> course.equals(slap.getCourse()))
        .toList();
    }

    // Find a Slap by ID
    public Optional<SlapEntity> findOne(Long id) {
        return slapRepository.findById(id);
    }

    // Check if a Slap exists by ID
    public boolean isExists(Long id) {
        return slapRepository.existsById(id);
    }

    //Partial Update
    public SlapEntity partialUpdate(Long id, SlapEntity slapEntity) {
        slapEntity.setSlapId(id);

        return slapRepository.findById(id).map(existingSlap -> {
            Optional.ofNullable(slapEntity.getCreator()).ifPresent(existingSlap::setCreator);
            Optional.ofNullable(slapEntity.getCourse()).ifPresent(existingSlap::setCourse);
            Optional.ofNullable(slapEntity.getContent()).ifPresent(existingSlap::setContent);

            // Save and return the updated Slap
            return slapRepository.save(existingSlap);
        }).orElseThrow(() -> new RuntimeException("Slap not found"));
    }

    //Delete a Slap
    public void delete(Long id){
        slapRepository.deleteById(id);
    }
}
