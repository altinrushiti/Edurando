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
    public void testUniqueAndValidMail() {
        // Create a mock user profile repository
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("Student", "Max", "Mustermann","max.mustermann@example.com","password"));
        users.add(new UserProfile("Student", "Max", "Musterfrau","max.musterfrau@example.com","password"));
        users.add(new UserProfile("Student", "Bennet", "Gurklies","bennet.gurklies@stud.th-luebeck.de","password"));
        UserProfileRepository userProfileRepository = mock(UserProfileRepository.class);
        when(userProfileRepository.findAll()).thenReturn(users);
        EmailValidator emailValidator = new EmailValidator(userProfileRepository);

        // Test non unique + non valid email
        boolean result1 = emailValidator.testMail("max.musterfrau@example.com").getFirst();
        assertFalse(result1);

        // Test unique + non valid email
        boolean result2 = emailValidator.testMail("john.doe@example.com").getFirst();
        assertFalse(result2);

        // Test non unique + valid email
        boolean result3 = emailValidator.testMail("bennet.gurklies@stud.th-luebeck.de").getFirst();
        assertFalse(result3);


        // Test unique + valid email
        boolean result4 = emailValidator.testMail("max.musterfrau@stud.th-luebeck.de").getFirst();
        assertTrue(result4);

    }

}
