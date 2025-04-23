package org.example.bloodbridge.service;

import org.example.bloodbridge.entity.BloodGroup;
import org.example.bloodbridge.entity.User;
import org.example.bloodbridge.entity.UserStatus;
import org.example.bloodbridge.repository.BloodGroupRepository;
import org.example.bloodbridge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BloodGroupRepository bloodGroupRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public UserService(UserRepository userRepository, BloodGroupRepository bloodGroupRepository) {
        this.userRepository = userRepository;
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public void registerUser(String name, String email, String phone, String bloodGroup, String location, MultipartFile document) throws IOException {
        // Find the blood group entity
        BloodGroup bloodGroupEntity = bloodGroupRepository
            .findByBloodGroup(bloodGroup)
            .orElseThrow(() -> new IllegalArgumentException("Invalid blood group"));

        // Save the document
        String documentPath = saveFile(document);

        // Create and save the user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setBloodGroup(bloodGroupEntity);
        user.setLocation(location);
        user.setDocumentPath(documentPath);
        user.setStatus(UserStatus.PENDING);

        userRepository.save(user);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Document file is required");
        }

        String uploadDir = System.getProperty("app.upload.dir", "uploads"); // Default to "uploads"
        File dir = new File(uploadDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Failed to create upload directory: " + uploadDir);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }
}
