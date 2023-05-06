package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
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
    public void testSetConfirmationAt() {
        // Arrange
        String token = "test_token";
        LocalDateTime now = LocalDateTime.now();
        //when(confirmationTokenRepository.updateConfirmedAt(eq(token), eq(now))).thenReturn(1);

        // Act
        int result = confirmationTokenService.setConfirmationAt(token);

        // Assert
        assertEquals(1, result);
        verify(confirmationTokenRepository, times(1)).updateConfirmedAt(eq(token), eq(now));
    }

}
