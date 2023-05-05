package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegistrationServiceTest {

    @Mock
    private UserProfileService userProfileService;

    @Mock
    private EmailValidatorTest emailValidator;
    @Mock
    private EmailSender emailSender;
    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private PasswordValidator passwordValidator;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void testRegisterSuccess() {
        // Mocking
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann", "max.mustermann@example.com", "password", "password");
        when(emailValidator.uniqueMail(anyString())).thenReturn(true);
        when(emailValidator.testMail(anyString())).thenReturn(true);
        when(passwordValidator.matchTest(anyString(), anyString())).thenReturn(true);
        when(passwordValidator.lengthTest(anyString())).thenReturn(true);
        when(passwordValidator.upperLowerCaseTest(anyString())).thenReturn(true);
        when(passwordValidator.digitTest(anyString())).thenReturn(true);
        when(passwordValidator.specialCharTest(anyString())).thenReturn(true);
        when(userProfileService.signUpUser(Mockito.any(UserProfile.class))).thenReturn("token");
        // Test
        String result = registrationService.register(request);
        // Verify
        assertEquals("(true, Registration was successful)\n", result);
        //Mockito.verify(emailSender).send(anyString(), anyString());
    }

    @Test
    public void testRegistrationFailure() {

        StringBuilder sb = new StringBuilder();

        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password");
        when(emailValidator.uniqueMail(Mockito.anyString())).thenReturn(false);
        sb.append("Email Exists\n");
        when(emailValidator.testMail(Mockito.anyString())).thenReturn(false);
        sb.append("E-Mail not valid\n");
        when(passwordValidator.matchTest(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        sb.append("Passwords do not match\n");
        when(passwordValidator.lengthTest(Mockito.anyString())).thenReturn(false);
        sb.append("Password needs minimum length of 8\n");
        when(passwordValidator.upperLowerCaseTest(Mockito.anyString())).thenReturn(false);
        sb.append("Password needs at least 1 upper and 1 lower case character\n");
        when(passwordValidator.digitTest(Mockito.anyString())).thenReturn(false);
        sb.append("Password needs at least 1 digit\n");
        when(passwordValidator.specialCharTest(Mockito.anyString())).thenReturn(false);
        sb.append("Password needs at least 1 special character\n");

        String final_msg = String.format("(%b , %s)", false, sb.toString());

        // Act
        String result = registrationService.register(request);

        // Assert
        Assertions.assertEquals(final_msg, result);

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
