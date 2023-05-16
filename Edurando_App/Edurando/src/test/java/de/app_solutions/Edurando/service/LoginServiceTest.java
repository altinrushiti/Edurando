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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginServiceTest {

    @MockBean
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
    @MockBean
    private RegistrationService registrationService;

    @Test
    public void loginUserNotVerifiedTest() {

        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann", "max.mustermann@stud.th-luebeck.de", "password", "password", true, true);
        registrationService.register(request);
        LoginRequest loginRequest = new LoginRequest("max.mustermann@stud.th-luebeck.de", "Test_123");

        Pair<Boolean, List<String>> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, List.of("Password or username is not correct.")),
                rs);
    }
}
