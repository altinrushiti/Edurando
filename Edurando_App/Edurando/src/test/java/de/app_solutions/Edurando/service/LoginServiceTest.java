package de.app_solutions.Edurando.service;


import de.app_solutions.Edurando.model.LoginRequest;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @MockBean
    private EmailValidator emailValidator;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private PasswordValidator passwordValidator;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegistrationService registrationService;
    @Test
    public void loginUserNotRegisteredTest() {
        UserProfile user = new UserProfile();
        user.setId(22L);
        user.setFirstName("Max");
        user.setLastName("Mustermann");

        user.setUsername("max.mustermann@stud.th-luebeck.de");
        user.setPassword("Test123!");
        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        LoginRequest loginRequest = new LoginRequest("max.musterfrau@stud.th-luebeck.de", "Pflaume234!");

        Pair<Boolean, String> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, "Invalid credentials. Try again!"), rs);
    }

    @Test
    public void loginUserNotVerifiedTest() {

        UserProfile user = new UserProfile();
        user.setId(22L);
        user.setFirstName("Max");
        user.setLastName("Mustermann");

        user.setUsername("max.mustermann@example4.com");
        user.setPassword("Test123!");

        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        LoginRequest loginRequest = new LoginRequest("max.mustermann@example4.com", "Test123!");

        Pair<Boolean, String> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, "Your account is not verified."), rs);
    }

    @Test
    public void loginUserIsLockedTest() {

        UserProfile user = new UserProfile();
        user.setId(22L);
        user.setFirstName("Max");
        user.setLastName("Mustermann");

        user.setUsername("max.mustermann@example5.com");
        user.setPassword("Test123!");
        user.setEnabled(true);
        user.setLocked(true);
        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        LoginRequest loginRequest = new LoginRequest("max.mustermann@example5.com", "Test123!");

        Pair<Boolean, String> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, "Your account is locked. Please contact the support."), rs);
    }

    @Test
    public void loginPasswordOrUsernameFailTest() {

        UserProfile user = new UserProfile();
        user.setId(22L);
        user.setFirstName("Max");
        user.setLastName("Mustermann");

        user.setUsername("max.mustermann@example6.com");
        user.setPassword("Test123!");
        user.setEnabled(true);

        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        LoginRequest loginRequest = new LoginRequest("max.mustermann@example6.com", "!Test123!");
        when(!bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.getPassword())).thenReturn(false);

        Pair<Boolean, String> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, "Invalid credentials. Try again!"), rs);
    }
    @Test
    public void loginSuccessTest() {

        UserProfile user = new UserProfile();
        user.setId(23L);
        user.setFirstName("Lena");
        user.setLastName("Musterfrau");

        user.setUsername("lena.musterfrau@example7.com");
        user.setPassword("Test123!");
        //userProfileService.signUpUser(user);
        user.setEnabled(true);

        when(userProfileRepository.findUserProfileByUsername(user.getUsername())).thenReturn(Optional.of(user));

        LoginRequest loginRequest = new LoginRequest("lena.musterfrau@example7.com", "Test123!");
        when(bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.getPassword())).thenReturn(true);
        Pair<Boolean, String> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(true, "Login successful."), rs);
    }

}
