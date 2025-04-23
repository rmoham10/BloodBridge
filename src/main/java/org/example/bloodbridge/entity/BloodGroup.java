package org.example.bloodbridge.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Blood_Groups")
public class BloodGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodGroupId;

    @Column(unique = true, nullable = false)
    private String bloodGroup;
}
