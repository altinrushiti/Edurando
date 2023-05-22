package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmailValidatorTest {
    UserProfileRepository userProfileRepository = mock(UserProfileRepository.class);
    EmailValidator emailValidator = new EmailValidator(userProfileRepository);

    @Test
    public void mailNotUniqueTest() {
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("Student", "Bennet", "Gurklies","bennet.gurklies@stud.th-luebeck.de","password"));
        when(userProfileRepository.findAll()).thenReturn(users);
        assertEquals(Pair.of(false,List.of("Email is not unique.")),
                emailValidator.testMail("bennet.gurklies@stud.th-luebeck.de"));
    }

    @Test
    public void mailNotValidTest() {
        assertEquals(Pair.of(false,List.of("Email is not valid.")),emailValidator.testMail("max.musterfrau@example.com"));
    }

    @Test
    public void mailNotUniqueAndNotValidTest() {
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("Student", "Bennet", "Gurklies","max.musterfrau@example.com","password"));
        when(userProfileRepository.findAll()).thenReturn(users);
        assertEquals(Pair.of(false,List.of("Email is not valid.","Email is not unique.")),
                emailValidator.testMail("max.musterfrau@example.com"));
    }

    @Test
    public void mailValidTest() {
        assertEquals(Pair.of(true,List.of()),
                emailValidator.testMail("max.mustermann@uni-ulm.de"));
    }




}
