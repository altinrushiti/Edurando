package de.app_solutions.Edurando.controller;

import static org.assertj.core.api.Assertions.assertThat;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest extends PostgresContainer {
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

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
        user1.setId(1L);
        user1.setProfilePictureReference("Edurando_App/Edurando/src/main/resources/p_placeholder.jpg");
        user1.setRating(0);
        // Set values for other properties if needed

        List<UserProfile> users = List.of(user1);

        when(userProfileRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"mobile\":'',\"profilePictureReference\":\"Edurando_App/Edurando/src/main/resources/p_placeholder.jpg\",\"personalBiography\":'',\"rating\":0,\"gender\":'',\"tutoringLocation\":'',\"username\":\"max.mustermann@stud.th-luebeck.de\",\"password\":\"MaxMustermann_123\",\"termsAgreed\":false,\"privacyAgreed\":false,\"subjects\":null,\"topics\":null,\"ratings\":null,\"role\":\"student\",\"locked\":false,\"enabled\":false,\"authorities\":[{\"authority\":\"student\"}],\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"accountNonLocked\":true}]"));
    }


}
