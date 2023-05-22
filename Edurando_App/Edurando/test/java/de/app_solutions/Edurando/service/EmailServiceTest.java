package de.app_solutions.Edurando.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailServiceTest {

    @Test
    public void testSend() {
        String to = "krish.kalra3@gmail.com";
        String email = "Hello, World!";
        EmailSender emailSender = mock(EmailSender.class);
        emailSender.send(to, email);
        verify(emailSender).send(to, email);
    }
}
