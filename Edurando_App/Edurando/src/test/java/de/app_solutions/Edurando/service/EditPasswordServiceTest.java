package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@RunWith(SpringRunner.class)
public class EditPasswordServiceTest {

    @Autowired
    private EditPasswordService editPasswordService;

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void editPasswordTestSuccess() {

        // Arrange
        UserProfile user1 = new UserProfile();
        user1.setId(1L);
        user1.setUsername("max.mustermann@example.com");
        user1.setPassword("Test123!");
        userProfileService.signUpUser(user1);
        EditPasswordRequest pwRequest = new EditPasswordRequest(1L, "Test123!", "Pflaume3!!", "Pflaume3!!");

        // Act
        Pair<Boolean, String> result = editPasswordService.editPassword(pwRequest);

        // Assert
        assertTrue(result.getFirst());
        assertEquals("Password changed successfully.",result.getSecond());
    }
    @Test
    public void PasswordDoesNotMatchTheCurrentUserPasswordTest() {

        // Arrange
        UserProfile user2 = new UserProfile();
        user2.setId(2L);
        user2.setUsername("max.mustermann@example2.com");
        user2.setPassword("Test123!");
        userProfileService.signUpUser(user2);
        EditPasswordRequest pwRequest = new EditPasswordRequest(2L, "Test123!!", "Pflaume3!!", "Pflaume3!!");

        // Act
        Pair<Boolean, String> result = editPasswordService.editPassword(pwRequest);

        // Assert
        assertFalse(result.getFirst());
        assertEquals("The current password entered does not match the user's current password.",result.getSecond());

    }

    @Test
    public void PasswordIsTheSameAsPreviousPasswordTest() {

        // Arrange
        UserProfile user3 = new UserProfile();
        user3.setId(3L);
        user3.setUsername("max.mustermann@example3.com");
        user3.setPassword("Test123!");
        userProfileService.signUpUser(user3);
        EditPasswordRequest pwRequest = new EditPasswordRequest(3L, "Test123!", "Test123!", "Test123!");


        // Act
        Pair<Boolean, String> result = editPasswordService.editPassword(pwRequest);

        // Assert
        assertFalse(result.getFirst());
        assertEquals("Password could not be changed because the new password is the same as the previous password.",result.getSecond());

    }
}
