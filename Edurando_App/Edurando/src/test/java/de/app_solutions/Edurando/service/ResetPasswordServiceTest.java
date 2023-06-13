package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResetPasswordServiceTest {

    private ResetPasswordService resetPasswordService;

    @Mock
    private UserProfileService userProfileService;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private EmailSender emailSender;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resetPasswordService = new ResetPasswordService(userProfileService, emailValidator, userProfileRepository,
                emailSender, bCryptPasswordEncoder, passwordValidator);
    }

    @Test
    public void testResetPassword_Successful() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "newPassword";

        UserProfile userProfile = new UserProfile();
        userProfile.setPassword("oldPassword");

        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.of(userProfile));
        when(bCryptPasswordEncoder.matches(newPassword, userProfile.getPassword())).thenReturn(false);
        when(passwordValidator.passwordTest(newPassword, newPasswordRepeat)).thenReturn(Pair.of(true, ""));

        Pair<Boolean, String> result = resetPasswordService.resetPassword(email, newPassword, newPasswordRepeat);

        assertTrue(result.getFirst());
        assertEquals("Password reset was successful.", result.getSecond());
        verify(userProfileRepository, times(1)).save(userProfile);
    }

    @Test
    public void testResetPassword_UserNotFound() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "newPassword";

        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.empty());

        Pair<Boolean, String> result = resetPasswordService.resetPassword(email, newPassword, newPasswordRepeat);

        assertFalse(result.getFirst());
        assertEquals("User with Email test@example.com was not found.", result.getSecond());
        verify(userProfileRepository, never()).save(any());
    }
    @Test
    public void testConfirmCode_Successful() {
        String confirmCode = "1234";
        String enteredConfirmCode = "1234";

        when(bCryptPasswordEncoder.matches(enteredConfirmCode, confirmCode)).thenReturn(true);

        Pair<Boolean, String> result = resetPasswordService.confirmCode(confirmCode, enteredConfirmCode);

        assertTrue(result.getFirst());
        assertEquals("Confirmation was successful.", result.getSecond());
    }

    @Test
    public void testConfirmCode_IncorrectCode() {
        String confirmCode = "1234";
        String enteredConfirmCode = "5678";

        when(bCryptPasswordEncoder.matches(enteredConfirmCode, confirmCode)).thenReturn(false);

        Pair<Boolean, String> result = resetPasswordService.confirmCode(confirmCode, enteredConfirmCode);

        assertFalse(result.getFirst());
        assertEquals("The entered confirmationcode is not correct.", result.getSecond());
    }


}
