package de.app_solutions.Edurando.controller;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class RegistrationControllerTest {
    RegistrationService registrationService = mock(RegistrationService.class);
    RegistrationController registrationController = mock(RegistrationController.class);

    @Test
    void testRegister() {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student","Max", "Mustermann", "max.mustermann@example.com", "password","password");
        when(registrationService.register(request)).thenReturn("Registration successful");

        RegistrationController registrationController = new RegistrationController(registrationService);

        // Act
        String result = registrationController.register(request);

        // Assert
        assertEquals("Registration successful", result);
        verify(registrationService, times(1)).register(request);
    }


    @Test
    void testConfirm() {
        // Arrange
        String token = "test_token";
        String expectedResult = "verifizierung_erfolgreich";
        when(registrationService.confirmToken(token)).thenReturn(expectedResult);

        // Act
        String result = registrationController.confirm(token);
        System.out.println("Result: " + expectedResult);

        // Assert
        assertEquals(expectedResult, result);
        verify(registrationService, times(1)).confirmToken(token);
    }

}
