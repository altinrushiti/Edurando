package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.ResetPasswordRequest;
import de.app_solutions.Edurando.service.ResetPasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@SpringBootTest
public class ResetPasswordControllerTest {
    private ResetPasswordService resetPasswordService = mock(ResetPasswordService.class);
    private ResetPasswordController resetPasswordController = new ResetPasswordController(resetPasswordService);

    @Test
    void testForgotPassword_Successful() {
        String email = "test@example.com";

        when(resetPasswordService.forgotPassword(email)).thenReturn(Pair.of(true, "Email sent"));

        ResponseEntity<String> response = resetPasswordController.forgotPassword(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email sent", response.getBody());
        verify(resetPasswordService).forgotPassword(email);
    }

    @Test
    void testForgotPassword_Failure() {
        String email = "test@example.com";

        when(resetPasswordService.forgotPassword(email)).thenReturn(Pair.of(false, "User not found"));

        ResponseEntity<String> response = resetPasswordController.forgotPassword(email);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(resetPasswordService).forgotPassword(email);
    }

    @Test
    void testConfirmCode_Successful() {
        String email = "test@example.com";
        String confirmationCode = "123456";

        when(resetPasswordService.confirmCode(email, confirmationCode)).thenReturn(Pair.of(true, "Code confirmed"));

        ResponseEntity<String> response = resetPasswordController.confirmCode(email, confirmationCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Code confirmed", response.getBody());
        verify(resetPasswordService).confirmCode(email, confirmationCode);
    }

    @Test
    void testConfirmCode_Failure() {
        String email = "test@example.com";
        String confirmationCode = "123456";

        when(resetPasswordService.confirmCode(email, confirmationCode)).thenReturn(Pair.of(false, "Invalid code"));

        ResponseEntity<String> response = resetPasswordController.confirmCode(email, confirmationCode);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid code", response.getBody());
        verify(resetPasswordService).confirmCode(email, confirmationCode);
    }

    @Test
    void testResetPassword_Successful() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        request.setNewPassword("newPassword");

        when(resetPasswordService.resetPassword(request)).thenReturn(Pair.of(true, "Password reset"));

        ResponseEntity<String> response = resetPasswordController.resetPassword(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password reset", response.getBody());
        verify(resetPasswordService).resetPassword(request);
    }

    @Test
    void testResetPassword_Failure() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setEmail("test@example.com");
        request.setNewPassword("newPassword");

        when(resetPasswordService.resetPassword(request)).thenReturn(Pair.of(false, "User not found"));

        ResponseEntity<String> response = resetPasswordController.resetPassword(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(resetPasswordService).resetPassword(request);
    }
}

 */
