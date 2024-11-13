package com.gould.slap.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gould.slap.domain.InstructionDocumentEntity;
import com.gould.slap.domain.ProjectEntity;
import com.gould.slap.services.InstructionDocumentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RestController
public class InstructionDocumentController {
    private final InstructionDocumentService instructionDocumentService;

    public InstructionDocumentController(InstructionDocumentService instructionDocumentService){
        this.instructionDocumentService = instructionDocumentService;
    }

    //Create a new Instruction Document
    @PostMapping(path = "/instruction_documents")
    public ResponseEntity<InstructionDocumentEntity> createInstructionDocumemt(@RequestBody InstructionDocumentEntity instructionDocumentEntity) {
        InstructionDocumentEntity createdInstructionDocumentEntity = instructionDocumentService.createInstructionDocument(instructionDocumentEntity);
        
        return new ResponseEntity<>(createdInstructionDocumentEntity, HttpStatus.CREATED);
    }

    //Get all Instruction Documents for a specific Project
    @GetMapping(path = "/instruction_documents/projectEntity/{projectEntity}")
    public List<InstructionDocumentEntity> getProjectDocuments(@PathVariable("projectEntity") ProjectEntity projectEntity) {
        return instructionDocumentService.findallbyCourse(projectEntity);
    }

    //Get an Instruction Document by ID
    @GetMapping(path = "/instruction_documents/{id}")
    public Optional<InstructionDocumentEntity> getInstructionDocumentsByID(@PathVariable("id")Long id) {
        return instructionDocumentService.findOne(id);
    }

    //Edit a Document
    @PatchMapping(path = "/instruction_documents/{id}")
    public ResponseEntity<InstructionDocumentEntity> editDocument(@PathVariable("id") Long id, @RequestBody InstructionDocumentEntity instructionDocumentEntity){
        if(!instructionDocumentService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InstructionDocumentEntity updatedDocument = instructionDocumentService.partialUpdate(id, instructionDocumentEntity);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }

    //Delete Document
    @DeleteMapping(path = "/instruction_documents/{id}")
    public ResponseEntity<Void> deleteInsructionDocument(@PathVariable("id") Long id){
        instructionDocumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
