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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long user_id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = true)
    private String middlename;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String dateofbirth;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    //Establish all the Relationships with User
    @OneToMany(mappedBy = "sender_id")
    private List<MessageEntity> sentMessageEntities;

    @OneToMany(mappedBy = "receiver_id")
    private List<MessageEntity> receivedMessageEntities;

    @OneToMany(mappedBy = "student")
    private List<SubmissionEntity> assignments;
}

