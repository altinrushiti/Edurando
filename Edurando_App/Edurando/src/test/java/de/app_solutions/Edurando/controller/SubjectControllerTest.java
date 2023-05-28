package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.UserProfile;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.web.servlet.function.ServerResponse.status;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EditSubjectRequest request;

    @MockBean
    private SubjectController subjectController;

    @Test
    void testUpdateSubjectData() throws Exception {

/*        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Teacher",
                "Max",
                "Musterfrau",
                email,
                "password");*/

         request = new EditSubjectRequest();

        request.setId(1L);
        request.setSubject("Math");
        request.setTopic("Algebra");


        String requestJson = new ObjectMapper().writeValueAsString(request);

        ResponseEntity<String> expectedResponse = ResponseEntity.ok().body("subject has been updated");

        when(subjectController.updateSubjectData(any(EditSubjectRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateSubjectData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(Objects.requireNonNull(expectedResponse.getBody())));
    }

}
