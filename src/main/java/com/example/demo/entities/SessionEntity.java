package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SessionId;

    private String refreshToken;

    @CreationTimestamp
    private LocalDateTime lastUsedAt;

    @ManyToOne
    private User user;
}
