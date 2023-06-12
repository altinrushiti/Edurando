package de.app_solutions.Edurando.service;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {
    @Test
    public void testSend() {
        String to = "krish.kalra3@gmail.com";
        String email = "Hello, World!";
        String subject = "Baum";
        EmailSender emailSender = mock(EmailSender.class);
        emailSender.send(to, email,subject);
        verify(emailSender).send(to, email,subject);
    }
}
