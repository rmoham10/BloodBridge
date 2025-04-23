package org.example.bloodbridge.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendVerificationNotification(String email, boolean isVerified) {
        String subject = isVerified ? "Registration Verified" : "Registration Rejected";
        String body = isVerified
            ? "Congratulations! Your registration has been verified."
            : "Unfortunately, your registration has been rejected.";

        // Use an email service to send the email (e.g., JavaMailSender)
    }

    public void sendRejectionNotification(String email, String rejectionReason) {
        String subject = "Registration Rejected";
        String body = "Your registration was rejected. Reason: " + rejectionReason;

        // Use an email service to send the email
    }
}
