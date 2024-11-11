package com.gould.slap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_id")
    @JsonIgnoreProperties({"evaluations"})
    private SubmissionEntity submissionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties({"evaluations", "sentMessages", "receivedMessages"})
    private UserEntity instructor;

    @Column(nullable = true)
    private String feedback;

    @Column(nullable = false)
    private int grade;
}
