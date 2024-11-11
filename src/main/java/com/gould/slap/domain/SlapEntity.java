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
@Table(name = "slaps")
public class SlapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slapId;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private String content;
}
