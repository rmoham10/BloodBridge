package org.example.bloodbridge.entity;

public enum UserStatus {
    PENDING,    // User registration is pending admin verification
    VERIFIED,   // User has been verified by admin
    REJECTED    // User has been rejected
}

