package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@TestPropertySource(locations = "classpath:application.properties")
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
