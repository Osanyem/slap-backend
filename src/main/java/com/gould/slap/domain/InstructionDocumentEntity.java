package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "instruction_documents")
public class InstructionDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructionDocumentId; // Renamed to camelCase

    @Column(nullable = false, unique = true)
    private String documentName; // Fixed typo (doucment -> document)

    @Column(nullable = true)
    private String description; // Nullable description

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectEntity;

}
