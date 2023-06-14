package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
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

        when(userProfileService.signUpUser(any(UserProfile.class))).thenReturn("token");
        // Test
        Pair<Boolean, String> result = registrationService.register(request);

        // Verify
        assertEquals(Pair.of(true, "Registration was successful."), result);
    }

    @Test
    public void testRegistrationFailure() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password", false, false);

        when(emailValidator.testMail(anyString())).thenReturn(Pair.of(false, "Email is not valid.Email is not unique."));
        when(passwordValidator.passwordTest(anyString(), anyString()))
                .thenReturn(Pair.of(false, "Passwords do not match,Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess."));

        String expectedMsg = "Passwords do not match,Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess.Email is not valid.Email is not unique.Terms of Service not agreed.Privacy Policy not agreed.";

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
        String result = registrationService.confirmToken(token).getSecond();

        // Assert
        assertEquals("Verification successful", result);
    }

    @Test
    void testConfirmTokenWithExpiredToken() {
        // Arrange
        String token = "test_token";
        UserProfile user = new UserProfile("Student", "Max", "Mustermann","max.mustermann6@example.com","password");
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().minusMinutes(5), user); // expires_at: now -> will throw exception
        when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

        // Act & Assert
        Pair<Boolean,String> expectedResponse = Pair.of(false,"token expired");
        Pair<Boolean,String> response = registrationService.confirmToken(token);
        //IllegalStateException exception = assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
        assertEquals(expectedResponse, response);
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
        Pair<Boolean,String> expectedResponse = Pair.of(false,"email already confirmed");
        Pair<Boolean,String> response = registrationService.confirmToken(token);
        assertEquals(expectedResponse, response);
    }


}
