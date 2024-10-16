package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_Id;

    @Column(nullable = false, unique = true)
    private String courseName;

    private String description;

    //We can also add a CourseCode field if wanted

    //Have to ensure the user that is added is a user with the role instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    //Establish Relationships

    //Required for the View Project Use Case
    @OneToMany(mappedBy = "course")
    private List<Project> projects;

    //Used for the View Courses Use Case to see how many students are in each course
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<User> students;
}
