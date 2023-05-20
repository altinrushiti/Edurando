package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailServiceTest extends PostgresContainer {
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    public void testSend() {
        String to = "krish.kalra3@gmail.com";
        String email = "Hello, World!";
        EmailSender emailSender = mock(EmailSender.class);
        emailSender.send(to, email);
        verify(emailSender).send(to, email);
    }
}
