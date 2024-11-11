package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, unique = true)
    private String courseName;

    @Column(nullable = false, length = 6, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private LocalDate enrollmentDeadline;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private UserEntity instructor;

    @OneToMany(mappedBy = "course")
    private List<ProjectEntity> projectEntities;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<UserEntity> students;
}
