package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//This entity is for the projects created within each course

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_Id;

    @Column(nullable = false, unique = true)
    private String projectName;

    private String description;

    //Establish Relationships
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity instructor;

    //View Instruction Documents Use Case
    @OneToMany(mappedBy = "projectEntity")
    private List<InstructionDocumentEntity> instructionDocs;

}