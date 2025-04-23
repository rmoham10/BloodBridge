package org.example.bloodbridge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class BloodBridgeApplication implements CommandLineRunner {

    private static final String UPLOAD_DIR = "uploads";

    public static void main(String[] args) {
        SpringApplication.run(BloodBridgeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Create the uploads directory if it does not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdir();
            if (created) {
                System.out.println("Uploads directory created at: " + uploadDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create uploads directory!");
            }
        } else {
            System.out.println("Uploads directory already exists at: " + uploadDir.getAbsolutePath());
        }
    }
}
