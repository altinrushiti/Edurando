package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegistrationServiceTest {

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private EmailValidator emailValidator;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private PasswordValidator passwordValidator;

    @Autowired
    private RegistrationService registrationService;

    @Test
    void testRegisterSuccess() {
        // Mocking
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann", "max.mustermann@example.com", "password", "password",true,true);
        when(emailValidator.testMail(anyString())).thenReturn(Pair.of(true,""));
        when(passwordValidator.passwordTest(anyString(), anyString())).thenReturn(Pair.of(true, ""));

        when(userProfileService.signUpUser(Mockito.any(UserProfile.class))).thenReturn("token");
        // Test
        Pair<Boolean,String> result = registrationService.register(request);

        // Verify
        assertEquals(Pair.of(true, "Registration was successful"), result);
        //Mockito.verify(emailSender).send(anyString(), anyString());
    }

    @Test
    public void testRegistrationFailure() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password", false, false);

        when(emailValidator.testMail(Mockito.anyString())).thenReturn(Pair.of(false, "Email is not valid,Email is not unique,"));
        when(passwordValidator.passwordTest(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Pair.of(false, "Passwords do not match,Password needs minimum length of 8,Password needs at least 1 upper and 1 lower case character,Password needs at least 1 digit,Password needs at least 1 special character,"));

        String expectedMsg = "Passwords do not match,Password needs minimum length of 8,Password needs at least 1 upper and 1 lower case character,Password needs at least 1 digit,Password needs at least 1 special character,Email is not valid,Email is not unique,Terms of Service not Agreed,Privacy Policy not Agreed";

        // Act
        Pair<Boolean, String> result = registrationService.register(request);

        // Assert
        Assertions.assertFalse(result.getFirst());
        Assertions.assertEquals(expectedMsg, result.getSecond());
    }


    @Test
    void testConfirmToken() {
        // Arrange
        String token = "test_token";
        UserProfile user = new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password");
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), user);
        when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

        // Act
        String result = registrationService.confirmToken(token);

        // Assert
        assertEquals("verifizierung_erfolgreich", result);
    }

    @Test
    void testConfirmTokenWithExpiredToken() {
        // Arrange
        String token = "test_token";
        UserProfile user = new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password");
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now().minusMinutes(5), LocalDateTime.now(), user); // expires_at: now -> will throw exception
        when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
        assertEquals("token expired", exception.getMessage());
    }

    @Test
    void testConfirmTokenWithAlreadyConfirmedToken() {
        // Arrange
        String token = "test_token";
        UserProfile user = new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password");
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), user);
        confirmationToken.setConfirmedAt(LocalDateTime.now()); // setConfirmedAt: now -> will throw exception
        when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
        assertEquals("email already confirmed", exception.getMessage());
    }


}
