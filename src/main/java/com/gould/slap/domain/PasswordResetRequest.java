package com.gould.slap.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//This entity is for the password reset requests that each user requests for

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PasswordResetRequests")
public class PasswordResetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //To show if the password request has been processed
    @Column(nullable = false)
    private boolean processed = false;
    
    //Might not be necessary but its there if we need it
    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Column
    private LocalDateTime processedAt;

}