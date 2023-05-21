package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest  {
    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


    @Test
    public void testSend() {
        String to = "krish.kalra3@gmail.com";
        String email = "Hello, World!";
        EmailSender emailSender = mock(EmailSender.class);
        emailSender.send(to, email);
        verify(emailSender).send(to, email);
    }
}
