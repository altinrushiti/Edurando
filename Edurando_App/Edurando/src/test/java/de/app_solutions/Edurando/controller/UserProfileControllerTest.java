package de.app_solutions.Edurando.controller;

import static org.assertj.core.api.Assertions.assertThat;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void getUserProfileById() throws Exception {
        UserProfile user = new UserProfile("Student", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user.setId(1L);
        user.setProfilePictureReference("../assets/p_placeholder.png");
        user.setRating(0);
        // Set values for other properties if needed

        when(userProfileRepository.findUserProfileById(user.getId())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/v1/profile/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
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
                    """));
    }

    @Test
    public void getTopUsers() throws Exception {
        UserProfile user1 = new UserProfile("Teacher", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user1.setId(1L);
        user1.setProfilePictureReference("../assets/p_placeholder.png");
        user1.setRating(1.0F);

        UserProfile user2 = new UserProfile("Teacher", "Maren", "Musterfrau", "Maren.Musterfrau@stud.th-luebeck.de", "Test_123");
        user2.setId(2L);
        user2.setProfilePictureReference("../assets/p_placeholder.png");
        user2.setRating(4.0F);
        // Set values for other properties if needed

        List<UserProfile> users = List.of(user1, user2);

        when(userProfileRepository.findTop10ByOrderByRatingDesc()).thenReturn(users.stream().sorted(Comparator.comparing(UserProfile::getRating).reversed()).toList());

        mockMvc.perform(get("/api/v1/top-users"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 2,
                                "firstName": "Maren",
                                "lastName": "Musterfrau",
                                "mobile": "",
                                "profilePictureReference": "../assets/p_placeholder.png",
                                "personalBiography": "",
                                "rating": 4.0,
                                "gender": "",
                                "tutoringLocation": "",
                                "username": "Maren.Musterfrau@stud.th-luebeck.de",
                                "role": "teacher",
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
                            },
                            {
                                "id": 1,
                                "firstName": "Max",
                                "lastName": "Mustermann",
                                "mobile": "",
                                "profilePictureReference": "../assets/p_placeholder.png",
                                "personalBiography": "",
                                "rating": 1.0,
                                "gender": "",
                                "tutoringLocation": "",
                                "username": "max.muster@stud.th-luebeck.de",
                                "role": "teacher",
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

    @Test
    public void getUserProfileByEmail() throws Exception {
        UserProfile user = new UserProfile("Student", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user.setId(1L);
        user.setProfilePictureReference("../assets/p_placeholder.png");
        user.setRating(0);
        // Set values for other properties if needed

        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/v1/profileByEmail/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
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
                    """));
    }

    @Test
    public void searchTest() throws Exception {
        UserProfile user = new UserProfile("Student", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user.setId(1L);
        user.setProfilePictureReference("../assets/p_placeholder.png");
        user.setRating(0);
        // Set values for other properties if needed

        String searchTerm = "Max";

        when(userProfileRepository.search(searchTerm)).thenReturn(List.of(user));

        mockMvc.perform(get("/api/v1/profiles/search/" + searchTerm))
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

    @Test
    public void uploadFileAndRemoveFileTest() throws Exception {
        UserProfile user = new UserProfile("Student", "Max", "Mustermann", "max.muster@stud.th-luebeck.de", "Test_123");
        user.setId(1L);
        user.setProfilePictureReference("../assets/p_placeholder.png");
        user.setRating(0);
        // Set values for other properties if needed

        when(userProfileRepository.findUserProfileById(user.getId())).thenReturn(Optional.of(user));
        // Erstelle den Inhalt der Datei
        byte[] fileContent = "Inhalt der Datei".getBytes(StandardCharsets.UTF_8);

        // Erstelle den MockMultipartFile
        MockMultipartFile file = new MockMultipartFile("file", "testfile.txt", MediaType.TEXT_PLAIN_VALUE, fileContent);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/upload")
                        .file(file)
                        .param("id", "1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/v1/removeImage/?id=1"))
                .andExpect(status().isOk());
    }


}
