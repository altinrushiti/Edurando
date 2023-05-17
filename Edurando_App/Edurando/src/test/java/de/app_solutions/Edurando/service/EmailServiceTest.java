package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.TestApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestApplicationConfig.class)
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
