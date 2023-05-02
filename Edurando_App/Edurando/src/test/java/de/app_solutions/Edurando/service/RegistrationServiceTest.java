package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.service.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private UserProfileService userProfileService;
    private EmailValidator emailValidator;
    private ConfirmationTokenService confirmationTokenService;
    private EmailSender emailSender;
    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setUp() {
        userProfileService = Mockito.mock(UserProfileService.class);
        emailValidator = Mockito.mock(EmailValidator.class);
        confirmationTokenService = Mockito.mock(ConfirmationTokenService.class);
        emailSender = Mockito.mock(EmailSender.class);
        passwordValidator = Mockito.mock(PasswordValidator.class);
        registrationService = new RegistrationService(userProfileService, emailValidator,
                confirmationTokenService, emailSender, passwordValidator);
    }

    @Test
    public void testRegistrationSuccess() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password");
        Mockito.when(emailValidator.testMail(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.matchTest(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.lengthTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.upperLowerCaseTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.digitTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.specialCharTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(emailValidator.mailExists(Mockito.anyString())).thenReturn(false);
        Mockito.when(userProfileService.signUpUser(Mockito.any())).thenReturn("token");

        // Act
        String result = registrationService.register(request);

        // Assert
        Assertions.assertEquals("(true, Registration was successful)\n", result);
    }

    @Test
    public void testRegistrationFailure() {

        StringBuilder sb = new StringBuilder();

        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann",
                "max.mustermann@example.com", "password", "password");
        Mockito.when(emailValidator.testMail(Mockito.anyString())).thenReturn(false);
        sb.append("E-Mail not valid\n");
        Mockito.when(passwordValidator.matchTest(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.lengthTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.upperLowerCaseTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.digitTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(passwordValidator.specialCharTest(Mockito.anyString())).thenReturn(true);
        Mockito.when(emailValidator.mailExists(Mockito.anyString())).thenReturn(false);
        Mockito.when(userProfileService.signUpUser(Mockito.any())).thenReturn("token");

        String final_msg = String.format("(%b , %s)", false, sb.toString());

        // Act
        String result = registrationService.register(request);

        // Assert
        Assertions.assertEquals(final_msg, result);

    }

}
