package de.app_solutions.Edurando.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.service.EditPasswordService;
import de.app_solutions.Edurando.service.RegistrationService;
import de.app_solutions.Edurando.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EditPasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EditPasswordService editPasswordService;
    @MockBean
    private EditPasswordController editPasswordController;
    @MockBean
    private RegistrationService registrationService;


    @Test
    void testEditPassword() throws Exception {

        // Create a sample password request
        EditPasswordRequest request = new EditPasswordRequest(1L, "oldPassword", "newPassword", "newPassword");
        String requestJson = new ObjectMapper().writeValueAsString(request);

        // Define the expected response
        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("Password changed successfully");
        when(editPasswordController.editPassword(any(EditPasswordRequest.class))).thenReturn(expectedResponse);

        // Perform the request and verify the response
        mockMvc.perform(put("/api/v1/editPassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Objects.requireNonNull(expectedResponse.getBody())));

    }

}

