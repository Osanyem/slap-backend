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
@Table(name = "evaluations")
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private SubmissionEntity submissionEntity;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private UserEntity instructor;

    @Column(nullable = true)
    private String feedback;

    @Column(nullable = false)
    private int grade;
}
