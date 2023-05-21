package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfirmationTokenServiceTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


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
