package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.eq;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserProfileServiceTest extends PostgresContainer {

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void signUpUserTest() throws Exception {
        String token1 = userProfileService.signUpUser(new UserProfile("Student", "Firstname", "Lastname", "email2@stud.th-luebeck.de", "Password_123"));
        String token2 = confirmationTokenRepository.findAll().get(confirmationTokenRepository.findAll().toArray().length - 1).getToken();

        assertEquals(token1, token2);
    }

    @Test
    public void enableAppUser() throws Exception {
        userProfileService.signUpUser(new UserProfile("Student", "Firstname", "Lastname", "email@stud.th-luebeck.de", "Password_123"));
        int enable = userProfileService.enableAppUser("email@stud.th-luebeck.de");
        boolean exists = userProfileRepository.findUserProfileByUsername("email@stud.th-luebeck.de").isPresent();
        boolean enabled = false;
        if (exists) {
            enabled = userProfileRepository.findUserProfileByUsername("email@stud.th-luebeck.de").get().isEnabled();
        }

        // Assert the enable value and enabled status
        assertEquals(1, enable);
        assertTrue(enabled);

    }


}
