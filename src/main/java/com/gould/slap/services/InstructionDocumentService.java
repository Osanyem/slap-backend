package com.gould.slap.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.gould.slap.domain.InstructionDocumentEntity;
import com.gould.slap.domain.ProjectEntity;
import com.gould.slap.repositories.InstructionDocumentRepository;

@Service
public class InstructionDocumentService {
    private final InstructionDocumentRepository instructionDocumentRepository;

    public InstructionDocumentService(InstructionDocumentRepository instructionDocumentRepository){
        this.instructionDocumentRepository = instructionDocumentRepository;
    }

    //Create a new Instruction Document
    public InstructionDocumentEntity createInstructionDocument(InstructionDocumentEntity instructionDocumentEntity){
        return instructionDocumentRepository.save(instructionDocumentEntity);
    }

    //Save A Document
    public InstructionDocumentEntity saveInstructionDocument(InstructionDocumentEntity instructionDocumentEntity){
        return instructionDocumentRepository.save(instructionDocumentEntity);
    }

    //Find All Documents
    public List<InstructionDocumentEntity> findall(){
        return StreamSupport.stream(instructionDocumentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //Find All Documents for a Specific Project
    public List<InstructionDocumentEntity> findallbyCourse(ProjectEntity project){
        return StreamSupport.stream(instructionDocumentRepository.findAll().spliterator(), false)
        .filter(instruction_documents -> project.equals(instruction_documents.getProjectEntity()))
        .toList();
    }

    //Find A Document By ID
    public Optional<InstructionDocumentEntity> findOne(Long id){
        return instructionDocumentRepository.findById(id);
    }

    //Check if a Document Exisits
    public boolean isExists(Long id) {
        return instructionDocumentRepository.existsById(id);
    }

    //Partial Update
    public InstructionDocumentEntity partialUpdate(Long id, InstructionDocumentEntity instructionDocumentEntity) {
        instructionDocumentEntity.setInstructionDocumentId(id);

        return instructionDocumentRepository.findById(id).map(exisitingDocument -> {
            Optional.ofNullable(instructionDocumentEntity.getDocumentName()).ifPresent(exisitingDocument::setDocumentName);
            Optional.ofNullable(instructionDocumentEntity.getDescription()).ifPresent(exisitingDocument::setDescription);
            Optional.ofNullable(instructionDocumentEntity.getProjectEntity()).ifPresent(exisitingDocument::setProjectEntity);
            
            // Save and return the updated Document
            return instructionDocumentRepository.save(exisitingDocument);
        }).orElseThrow(() -> new RuntimeException("Document not found"));
    }

    //Delete a Document
    public void delete(Long id){
        instructionDocumentRepository.deleteById(id);
    }
}
