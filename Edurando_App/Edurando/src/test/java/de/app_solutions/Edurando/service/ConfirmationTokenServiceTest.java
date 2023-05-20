package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.EdurandoApplication;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;



@SpringBootTest
public class ConfirmationTokenServiceTest extends PostgresContainer {

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void testSaveConfirmationToken() {
        // Arrange
        ConfirmationToken token = new ConfirmationToken("test_token", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), null);

        // Act
        confirmationTokenService.saveConfirmationToken(token);

        // Assert
        verify(confirmationTokenRepository, times(1)).save(token);
    }

    @Test
    void testGetToken() {
        // Arrange
        String token = "test_token";
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), null);
        when(confirmationTokenRepository.findByToken(token)).thenReturn(Optional.of(confirmationToken));

        // Act
        Optional<ConfirmationToken> result = confirmationTokenService.getToken(token);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(confirmationToken, result.get());
    }




}
