package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserProfileServiceTest {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

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
        boolean enabled = userProfileRepository.findUserProfileByUsername("email@stud.th-luebeck.de").get().isEnabled();
        assertEquals(enable == 1, enabled);

    }

}
