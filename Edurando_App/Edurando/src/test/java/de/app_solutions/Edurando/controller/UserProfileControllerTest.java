package de.app_solutions.Edurando.controller;

import static org.assertj.core.api.Assertions.assertThat;

import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileController userProfileController;


    @Test
    public void controllerNotNull() throws Exception {
        assertThat(userProfileController).isNotNull();
    }

    @Test
    public void getUserProfiles_returnsEmptyList() throws Exception {
        // arrange
        when(userProfileRepository.findAll()).thenReturn(Collections.emptyList());


        // act & assert
        mockMvc.perform(get("/api/v1/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getUserProfiles_returnsList() throws Exception {
        UserProfile user1 = new UserProfile("Student", "Max", "Mustermann", "max.mustermann@stud.th-luebeck.de", "MaxMustermann_123");
        List<UserProfile> users = List.of(user1);

        when(userProfileRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"mobile\":null,\"profilePictureReference\":null,\"personalBiography\":null,\"rating\":null,\"gender\":null,\"username\":\"max.mustermann@stud.th-luebeck.de\",\"password\":\"MaxMustermann_123\",\"subjects\":null,\"topics\":null,\"ratings\":null,\"role\":\"student\",\"address\":null,\"locked\":false,\"enabled\":false,\"authorities\":[{\"authority\":\"student\"}],\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true}]"));

    }

}
