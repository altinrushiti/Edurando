package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserProfileServiceTest {

    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


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
