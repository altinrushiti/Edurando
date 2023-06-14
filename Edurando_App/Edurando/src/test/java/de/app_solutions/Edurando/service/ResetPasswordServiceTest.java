
package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationCode;
import de.app_solutions.Edurando.model.ResetPasswordRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.ConfirmationCodeRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
@SpringBootTest
@RunWith(SpringRunner.class)
class ResetPasswordServiceTest {

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private EmailValidator emailValidator;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private PasswordValidator passwordValidator;

    @MockBean
    private ConfirmationCodeRepository confirmationCodeRepository;

    @Autowired
    private ResetPasswordService resetPasswordService;


    @Test
    public void testResetPassword_SuccessfulReset() {

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test@example.com");
        userProfile.setPassword("oldPassword");
        userProfileRepository.save(userProfile);

        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setCode("1234");
        confirmationCode.setConfirmed(true);
        confirmationCode.setUser(userProfile);
        confirmationCodeRepository.save(confirmationCode);

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        request.setNewPassword("newPassword");
        request.setNewPasswordRepeat("newPassword");


        when(userProfileRepository.findUserProfileByUsername(request.getEmail()))
                .thenReturn(Optional.of(userProfile));
        when(confirmationCodeRepository.findByUser_Username(userProfile.getUsername()))
                .thenReturn(Optional.of(confirmationCode));
        when(passwordValidator.passwordTest(request.getNewPassword(), request.getNewPasswordRepeat()))
                .thenReturn(Pair.of(true, "Valid password"));
        when(bCryptPasswordEncoder.matches(request.getNewPassword(), userProfile.getPassword()))
                .thenReturn(false);


        Pair<Boolean, String> resetResult = resetPasswordService.resetPassword(request);


        assertTrue(resetResult.getFirst());
        assertEquals("Password reset was successful.", resetResult.getSecond());

    }

    @Test
    void testResetPassword_UserNotFound() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "newPassword";

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail(email);
        request.setNewPassword(newPassword);
        request.setNewPasswordRepeat(newPasswordRepeat);

        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.empty());

        Pair<Boolean, String> result = resetPasswordService.resetPassword(request);

        assertFalse(result.getFirst());
        assertEquals("User with Email test@example.com was not found.", result.getSecond());
        verify(userProfileRepository, never()).save(any(UserProfile.class));
    }


    @Test
    void inValidConformationCode() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "newPassword";

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail(email);
        request.setNewPassword(newPassword);
        request.setNewPasswordRepeat(newPasswordRepeat);

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test@example.com");
        userProfile.setPassword("oldPassword");
        userProfileRepository.save(userProfile);

        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.of(userProfile));
        when(confirmationCodeRepository.findByUser_Username(email)).thenReturn(Optional.empty());

        Pair<Boolean, String> result = resetPasswordService.resetPassword(request);

        assertFalse(result.getFirst());
        assertEquals("Passwordrequest-Token does not exist.", result.getSecond());

    }

    @Test
    void notConfirmedCode() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test@example.com");
        userProfile.setPassword("oldPassword");
        userProfileRepository.save(userProfile);

        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setCode("1234");
        confirmationCode.setConfirmed(false);
        confirmationCode.setUser(userProfile);
        confirmationCodeRepository.save(confirmationCode);

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        request.setNewPassword("newPassword");
        request.setNewPasswordRepeat("newPassword");


        when(userProfileRepository.findUserProfileByUsername(request.getEmail()))
                .thenReturn(Optional.of(userProfile));
        when(confirmationCodeRepository.findByUser_Username(userProfile.getUsername()))
                .thenReturn(Optional.of(confirmationCode));

        Pair<Boolean, String> result = resetPasswordService.resetPassword(request);

        assertFalse(result.getFirst());
        assertEquals("Passwordrequest-Token is not confirmed.", result.getSecond());
    }

    @Test
    public void oldPasswordSameNewPassword() {

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test@example.com");
        userProfile.setPassword("oldPassword");
        userProfileRepository.save(userProfile);

        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setCode("1234");
        confirmationCode.setConfirmed(true);
        confirmationCode.setUser(userProfile);
        confirmationCodeRepository.save(confirmationCode);

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        request.setNewPassword("oldPassword");
        request.setNewPasswordRepeat("oldPassword");


        when(userProfileRepository.findUserProfileByUsername(request.getEmail()))
                .thenReturn(Optional.of(userProfile));
        when(confirmationCodeRepository.findByUser_Username(userProfile.getUsername()))
                .thenReturn(Optional.of(confirmationCode));
        when(passwordValidator.passwordTest(request.getNewPassword(), request.getNewPasswordRepeat()))
                .thenReturn(Pair.of(true, "Valid password"));
        when(bCryptPasswordEncoder.matches(request.getNewPassword(), userProfile.getPassword()))
                .thenReturn(true);


        Pair<Boolean, String> resetResult = resetPasswordService.resetPassword(request);


        assertFalse(resetResult.getFirst());
        assertEquals("Password could not be changed because the new password is the same as the previous password.", resetResult.getSecond());

    }

    @Test
    public void confirmationCodeIsEmpty() {

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test@example.com");
        userProfile.setPassword("oldPassword");
        userProfileRepository.save(userProfile);

        Pair<Boolean, String> resetResult = resetPasswordService.confirmCode("test@example.com", "");

        assertFalse(resetResult.getFirst());
        assertEquals("Passwordrequest-Token does not exist.", resetResult.getSecond());

    }

    @Test
    public void testConfirmCode_UserNotFound() {

        String email = "test@example.com";
        String enteredConfirmCode = "123456";

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(email);

        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setUser(userProfile);
        confirmationCode.setCode("$2a$10$G32I6Ri4yM3TrCpWhlJIie6p4ZK5hyrUCQ89K2w1rB8dmH3N46RV2");
        confirmationCode.setConfirmed(false);

        when(confirmationCodeRepository.findByUser_Username(email))
                .thenReturn(Optional.of(confirmationCode));
        when(userProfileRepository.findUserProfileByUsername(confirmationCode.getUser().getUsername()))
                .thenReturn(Optional.empty());


        Pair<Boolean, String> confirmationResult = resetPasswordService.confirmCode(email, enteredConfirmCode);


        assertFalse(confirmationResult.getFirst());
        assertEquals("User with Email test@example.com was not found.", confirmationResult.getSecond());

    }

    @Test
    void testConfirmCode_Successful() {
        String email = "test@example.com";
        String confirmationCode = "123456";

        UserProfile user = new UserProfile();
        user.setUsername(email);
        userProfileRepository.save(user);

        ConfirmationCode code = new ConfirmationCode();
        code.setUser(user);
        code.setConfirmed(false);
        code.setCode("$2a$10$GcV8E/AIY4O6aSlqyMsfueRuk.1T2uQU4yEDwSLABZlRY/1Hc0.m6");

        when(confirmationCodeRepository.findByUser_Username(email)).thenReturn(Optional.of(code));
        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(eq(confirmationCode), anyString())).thenReturn(true);

        Pair<Boolean, String> result = resetPasswordService.confirmCode(email, confirmationCode);

        assertTrue(result.getFirst());
        assertEquals("Confirmation was successful.", result.getSecond());
        assertTrue(code.isConfirmed());

    }

    @Test
    void testConfirmCode_IsNotSuccessful() {
        String email = "test@example.com";
        String confirmationCode = "123456";

        UserProfile user = new UserProfile();
        user.setUsername(email);
        userProfileRepository.save(user);

        ConfirmationCode code = new ConfirmationCode();
        code.setUser(user);
        code.setConfirmed(false);
        code.setCode("$2a$10$GcV8E/AIY4O6aSlqyMsfueRuk.1T2uQU4yEDwSLABZlRY/1Hc0.m6");

        when(confirmationCodeRepository.findByUser_Username(email)).thenReturn(Optional.of(code));
        when(userProfileRepository.findUserProfileByUsername(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(eq(confirmationCode), anyString())).thenReturn(false);

        Pair<Boolean, String> result = resetPasswordService.confirmCode(email, confirmationCode);

        assertFalse(result.getFirst());
        assertEquals("The entered confirmationcode is not correct.", result.getSecond());
        assertFalse(code.isConfirmed());
    }

    @Test
    public void testConfirmCode_RequestNotValid() {

        String email = "test@example.com";
        String enteredConfirmCode = "123456";

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(email);

        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setUser(userProfile);
        confirmationCode.setCode("$2a$10$G32I6Ri4yM3TrCpWhlJIie6p4ZK5hyrUCQ89K2w1rB8dmH3N46RV2");
        confirmationCode.setConfirmed(true);

        when(confirmationCodeRepository.findByUser_Username(email))
                .thenReturn(Optional.of(confirmationCode));
        when(userProfileRepository.findUserProfileByUsername(confirmationCode.getUser().getUsername()))
                .thenReturn(Optional.of(userProfile));


        Pair<Boolean, String> confirmationResult = resetPasswordService.confirmCode(email, enteredConfirmCode);


        assertFalse(confirmationResult.getFirst());
        assertEquals("Request not valid.", confirmationResult.getSecond());
    }

    @Test
    public void testForgotPassword_AccountNotVerified() {
        String email = "test@example.com";

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(email);
        userProfile.setEnabled(false);

        when(userProfileRepository.findUserProfileByUsername(email))
                .thenReturn(Optional.of(userProfile));

        Pair<Boolean, String> result = resetPasswordService.forgotPassword(email);


        assertFalse(result.getFirst());
        assertEquals("Your account is not verified.", result.getSecond());
    }

    @Test
    public void testForgotPassword_AccountLocked() {

        String email = "test@example.com";

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(email);
        userProfile.setEnabled(true);
        userProfile.setLocked(true);

        when(userProfileRepository.findUserProfileByUsername(email))
                .thenReturn(Optional.of(userProfile));

        Pair<Boolean, String> result = resetPasswordService.forgotPassword(email);

        assertFalse(result.getFirst());
        assertEquals("Your account is locked. Please contact the support.", result.getSecond());
    }

    @Test
    public void testForgotPassword_OldConfirmationCodeExists() {

        String email = "test@example.com";

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(email);
        userProfile.setEnabled(true);
        userProfile.setLocked(false);

        ConfirmationCode oldConfirmationCode = new ConfirmationCode();
        oldConfirmationCode.setUser(userProfile);

        when(userProfileRepository.findUserProfileByUsername(email))
                .thenReturn(Optional.of(userProfile));
        when(confirmationCodeRepository.findByUser_Username(userProfile.getUsername()))
                .thenReturn(Optional.of(oldConfirmationCode));


        Pair<Boolean, String> result = resetPasswordService.forgotPassword(email);


        assertTrue(result.getFirst());
        assertEquals("Email sent successfully.", result.getSecond());
    }

    @Test
    public void testForgotPassword_UserNotFound() {
        String email = "test@example.com";

        when(userProfileRepository.findUserProfileByUsername(email))
                .thenReturn(Optional.empty());

        Pair<Boolean, String> result = resetPasswordService.forgotPassword(email);

        assertFalse(result.getFirst());
        assertEquals("User with Email test@example.com was not found.", result.getSecond());
    }
}



