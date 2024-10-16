package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity is for SLAPS that are created by admins or instructors to send out to the users

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SLAPS")
public class Slap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slap_Id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator_id;  

    //Admins can send to all users so is course_id is not needed for them but will be needed for instructors
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course; 

    private String content;


}
