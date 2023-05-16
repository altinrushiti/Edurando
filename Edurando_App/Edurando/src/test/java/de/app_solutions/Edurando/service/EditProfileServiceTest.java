package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EditProfileServiceTest {
    @Autowired
    private EditProfileService editProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void editPasswordTest() {



        // Arrange
        UserProfile user1 = new UserProfile();
        user1.setId(1L);
        user1.setPassword("Test123!");

        EditPasswordRequest pwRequest = new EditPasswordRequest(1L, "Test123!", "Pflaume3!!", "Pflaume3!!");

        when(userProfileRepository.findUserProfileById(1L)).thenReturn(Optional.of(user1));
        when(bCryptPasswordEncoder.matches(user1.getPassword(), pwRequest.getCurrentPassword())).thenReturn(true);
        when(bCryptPasswordEncoder.matches(user1.getPassword(), pwRequest.getNewPassword())).thenReturn(false);

        // Act
        Pair<Boolean, List<String>> result = editProfileService.editPassword(pwRequest);

        when(bCryptPasswordEncoder.matches(user1.getPassword(), pwRequest.getCurrentPassword())).thenReturn(false);
        when(bCryptPasswordEncoder.matches(user1.getPassword(), pwRequest.getNewPassword())).thenReturn(true);

        // Assert
        assertTrue(result.getFirst());
    }

    @Test
    public void editPersonalDataTest() {
        // Arrange
        UserProfile user = new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password");

        // ... set other properties

        EditPersonalDataRequest request = new EditPersonalDataRequest();
        request.setId(1L);
        request.setFirstName("Jane");
        request.setLastName("Smith");
        request.setGender("Male");
        request.setRole("Lehrer");
        request.setStreet("Lehmstrasse");
        request.setHouseNumber("62");
        request.setCity("Berlin");
        request.setState("Berlin");
        request.setPostCode("23421");

        // ... set other properties

        // Simulate the behavior of retrieving the user profile from the repository
        when(userProfileRepository.findUserProfileById(1L)).thenReturn(Optional.of(user));

        // Act
        Pair<Boolean, String> result = editProfileService.editPersonalData(request);

        // Assert
        assertTrue(result.getFirst());
        assertEquals("The processing was successful", result.getSecond());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals(Role.teacher, user.getRole());
        assertEquals("Male", user.getGender());

        // ... assert other properties

    }

}
