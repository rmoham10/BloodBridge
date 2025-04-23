package org.example.bloodbridge.service;

import org.example.bloodbridge.entity.BloodGroup;
import org.example.bloodbridge.entity.User;
import org.example.bloodbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final Map<String, String> adminCredentials = new HashMap<>();

    public AdminService() {
        adminCredentials.put("admin", "password"); // Replace with secure storage for production
    }

    public boolean authenticate(String username, String password) {
        return adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password);
    }

    public void requestBlood(Long bloodGroupId, String clinicDetails) {
        // Fetch users by blood_group_id
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setBloodGroupId(bloodGroupId);
        List<User> donors = userRepository.findByBloodGroup(bloodGroup);

        // Construct email body
        String emailBody = "Urgent Blood Requirement:\n\n" +
            "Requested Blood Group ID: " + bloodGroupId + "\n" +
            "Clinic/Hospital Details: " + clinicDetails + "\n\n" +
            "Please respond if you can donate. Thank you for your support.";

        // Send email to each donor
        for (User donor : donors) {
            emailService.sendEmail(donor.getEmail(), "Blood Donation Request", emailBody);
        }
    }

}
