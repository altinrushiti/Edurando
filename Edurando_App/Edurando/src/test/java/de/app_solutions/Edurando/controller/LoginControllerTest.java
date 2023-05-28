package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.LoginRequest;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.service.LoginService;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginController loginController;

    @MockBean
    private LoginService loginService;

    @Test
    void testLogin() throws Exception {

        RegistrationRequest request = new RegistrationRequest("Student","Maxi","Musterfrau","maxi.musterfrau@stud.th-luebeck.de","Test_123","Test_123",true,true);
        LoginRequest loginRequest = new LoginRequest("maxi.musterfrau@stud.th-luebeck.de","Test_123");
        String requestJson = new ObjectMapper().writeValueAsString(loginRequest);

        ResponseEntity<String> expectedresponse = ResponseEntity.ok().body("Login successful.");
        when(loginController.login(any(LoginRequest.class))).thenReturn(expectedresponse);

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Objects.requireNonNull(expectedresponse.getBody())));
    }

}
