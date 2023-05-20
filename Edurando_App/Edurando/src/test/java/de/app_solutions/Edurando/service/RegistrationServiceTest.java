package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegistrationServiceTest extends PostgresContainer {

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

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
        when(emailValidator.testMail(anyString())).thenReturn(Pair.of(true,List.of()));
        when(passwordValidator.passwordTest(anyString(), anyString())).thenReturn(Pair.of(true, List.of()));

        when(userProfileService.signUpUser(Mockito.any(UserProfile.class))).thenReturn("token");
        // Test
        Pair<Boolean, List<String>> result = registrationService.register(request);

        // Verify
        assertEquals(Pair.of(true, List.of("Registration was successful.")), result);
        //Mockito.verify(emailSender).send(anyString(), anyString());
    }

    @Test
    public void testRegistrationFailure() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password", false, false);

        when(emailValidator.testMail(Mockito.anyString())).thenReturn(Pair.of(false, List.of("Email is not valid.","Email is not unique.")));
        when(passwordValidator.passwordTest(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Pair.of(false, List.of("Passwords do not match","Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess.")));

        List<String> expectedMsg = List.of("Passwords do not match","Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess.","Email is not valid.","Email is not unique.","Terms of Service not agreed.","Privacy Policy not agreed.");

        // Act
        Pair<Boolean, List<String>> result = registrationService.register(request);

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
