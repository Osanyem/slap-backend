package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//This entity is for the messages that are sent between users

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_Id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    //Establish the relationships
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender_id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver_id;

}