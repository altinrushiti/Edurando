package de.app_solutions.Edurando.controller;

import static org.assertj.core.api.Assertions.assertThat;

import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

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
@RunWith(SpringRunner.class)
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
        UserProfile user1 = new UserProfile("Student", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user1.setId(1L);
        user1.setProfilePictureReference("../assets/p_placeholder.png");
        user1.setRating(0);
        // Set values for other properties if needed

        List<UserProfile> users = List.of(user1);

        when(userProfileRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "firstName": "Max",
                                "lastName": "Mustermann",
                                "mobile": "",
                                "profilePictureReference": "../assets/p_placeholder.png",
                                "personalBiography": "",
                                "rating": 0.0,
                                "gender": "",
                                "tutoringLocation": "",
                                "username": "max.muster@stud.th-luebeck.de",
                                "role": "student",
                                "subjects": null,
                                "topics": null,
                                "ratings": null,
                                "address": {
                                    "id": null,
                                    "street": "",
                                    "houseNumber": "",
                                    "city": "",
                                    "postCode": -1,
                                    "state": "",
                                    "userProfile": null
                                }
                            }
                        ]"""));
    }


}
