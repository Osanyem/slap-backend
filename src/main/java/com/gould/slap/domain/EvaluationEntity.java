package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity is the for evaluations that instructors send

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evaluations")
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluation_Id;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private SubmissionEntity submissionEntity;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private UserEntity instructor;

    @Column(nullable = false)
    private String feedback;

    @Column(nullable = false)
    private int grade;


}
