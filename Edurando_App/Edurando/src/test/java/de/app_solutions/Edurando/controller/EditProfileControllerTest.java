package de.app_solutions.Edurando.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.service.EditPasswordService;
import de.app_solutions.Edurando.service.EditProfileService;
import de.app_solutions.Edurando.service.RegistrationService;
import de.app_solutions.Edurando.service.UserProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EditProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EditProfileService editProfileService;
    @MockBean
    private EditProfileController editProfileController;

    @Test
    void testEditProfile() throws Exception {

        EditPersonalDataRequest request = new EditPersonalDataRequest();
        request.setId(4L);
        request.setFirstName("Jane");
        request.setLastName("Smith");
        request.setGender("Male");
        request.setPersonalBiography("");
        request.setMobile("");
        request.setRole("Lehrer");
        request.setStreet("Lehmstrasse");
        request.setHouseNumber("62");
        request.setCity("Berlin");
        request.setState("Berlin");
        request.setPostCode("23421");

        String requestJson = new ObjectMapper().writeValueAsString(request);

        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("The processing was successful.");
        when(editProfileController.updatePersonalData(any(EditPersonalDataRequest.class))).thenReturn(expectedResponse);

        // Perform the request and verify the response
        mockMvc.perform(put("/api/v1/updatePersonalData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Objects.requireNonNull(expectedResponse.getBody())));

    }


}
