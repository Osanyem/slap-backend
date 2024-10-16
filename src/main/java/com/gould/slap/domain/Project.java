package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity is for the projects created within each course

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_Id;

    @Column(nullable = false, unique = true)
    private String projectName;

    private String description;

    //Establish Relationships
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User instructor;

    //View Instruction Documents Use Case
    @OneToMany(mappedBy = "project")
    private List<InstructionDocument> instructionDocs;

}

