package de.app_solutions.Edurando.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private RegistrationController registrationController;

    @Test
    void testRegister() throws Exception {
        // Arrange
        RegistrationRequest request = new RegistrationRequest("Student","Max", "Mustermann", "max.mustermann@example.com", "password","password");
        String requestJson = new ObjectMapper().writeValueAsString(request); // serializing the request object to JSON string
        when(registrationService.register(request)).thenReturn("Registration successful");

        RegistrationController registrationController = new RegistrationController(registrationService);

        // Act
        String result = registrationController.register(request);

        // Assert
        // Act and Assert
        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
        //verify(registrationService, times(1)).register(request);
    }


    @Test
    void testConfirm() throws Exception {
        // Arrange
        String token = "test_token";

        String expectedResult = "verifizierung_erfolgreich";
        when(registrationService.confirmToken(token)).thenReturn(expectedResult);

        // Act
        mockMvc.perform(get("/api/v1/confirm/?token="+token))
                        .andExpect(status().isOk())
                                .andExpect(content().string(expectedResult));



        //verify(registrationService, times(1)).confirmToken(token);
    }

}
