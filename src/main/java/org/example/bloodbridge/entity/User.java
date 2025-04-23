package org.example.bloodbridge.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "blood_group_id", nullable = false)
    private BloodGroup bloodGroup;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String documentPath;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.PENDING; // Default status is PENDING
}
