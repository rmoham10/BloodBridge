package org.example.bloodbridge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailSenderTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testJavaMailSenderBean() {
        assertNotNull(javaMailSender, "JavaMailSender bean should be available");
    }
}

