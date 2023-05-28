package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EditProfileServiceTest {

    @Autowired
    private EditProfileService editProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;
    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void editPersonalDataTest() {
        // Arrange
        UserProfile user4 = new UserProfile();
        user4.setId(4L);
        user4.setFirstName("Max");
        user4.setLastName("Mustermann");

        user4.setUsername("max.mustermann@example4.com");
        user4.setPassword("Test123!");

        Address address = new Address();
        address.setStreet("TestStreet");
        address.setHouseNumber("TestHouseNumber");
        address.setCity("TestCity");
        address.setState("TestState");
        address.setPostCode(12345);
        //address.setUserProfile(List.of(user4));
        user4.setAddress(address);

        //userProfileService.signUpUser(user4);
// Simulate the behavior of retrieving the user profile from the repository
        when(userProfileRepository.findUserProfileById(4L)).thenReturn(Optional.of(user4));


        EditPersonalDataRequest request = new EditPersonalDataRequest();
        request.setId(4L);
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

        // Act
        editProfileService.editPersonalData(request);

        // Assert
        assertEquals("Jane", user4.getFirstName());
        assertEquals("Smith", user4.getLastName());
        assertEquals(Role.teacher, user4.getRole());
        assertEquals("Male", user4.getGender());

        // ... assert other properties

    }

}
