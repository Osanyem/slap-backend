package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity is for every Instruction Documents that are uploaded by the instructor for each project

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "instruction_documents")
public class InstructionDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructionDocument_Id;

    @Column(nullable = false, unique = true)
    private String doucmentName;

    private String description;

    //Have to ensure the user that is added is a user with the role instructor
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

}