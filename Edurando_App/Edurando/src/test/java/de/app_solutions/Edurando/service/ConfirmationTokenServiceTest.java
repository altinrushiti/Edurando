package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfirmationTokenServiceTest {


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
