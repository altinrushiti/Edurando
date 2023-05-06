package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmailValidatorTest {
    @Test
    public void testUniqueMail() {
        // Create a mock user profile repository
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password"));
        users.add(new UserProfile("Student", "Max", "Musterfrau","max.musterfrau@example.com","password"));
        UserProfileRepository userProfileRepository = mock(UserProfileRepository.class);
        when(userProfileRepository.findAll()).thenReturn(users);
        EmailValidator emailValidator = new EmailValidator(userProfileRepository);
        // Test unique email
        boolean result1 = emailValidator.uniqueMail("max.musterfrau@example.com");
        assertFalse(result1);

        // Test non-unique email
        boolean result2 = emailValidator.uniqueMail("john.doe@example.com");
        assertTrue(result2);
    }

    @Test
    public void testMail() {
        UserProfileRepository userProfileRepository = mock(UserProfileRepository.class);
        when(userProfileRepository.findAll()).thenReturn(Collections.emptyList());
        EmailValidator emailValidator = new EmailValidator(userProfileRepository);
        // Test unique email
        boolean result1 = emailValidator.testMail("max.musterfrau@stud.th-luebeck.de");
        assertTrue(result1);

        // Test non-unique email
        boolean result2 = emailValidator.testMail("john.doe@example.com");
        assertFalse(result2);
    }
}
