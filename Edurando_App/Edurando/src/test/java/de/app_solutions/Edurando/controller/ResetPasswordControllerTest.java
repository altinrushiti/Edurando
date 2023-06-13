package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.service.ResetPasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest

public class ResetPasswordControllerTest {

    @Mock
    private ResetPasswordService resetPasswordService;

    @InjectMocks
    private ResetPasswordController resetPasswordController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(resetPasswordController).build();
    }

    @Test
    public void testForgotPassword_Success() throws Exception {

        String email = "test@example.com";
        when(resetPasswordService.forgotPassword(email)).thenReturn(Pair.of(true, "Success"));


        MvcResult result = mockMvc.perform(post("/api/v1/resetPassword")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        verify(resetPasswordService).forgotPassword(email);

    }

    @Test
    public void testForgotPassword_Failure() throws Exception {

        String email = "test@example.com";
        when(resetPasswordService.forgotPassword(email)).thenReturn(Pair.of(false, "Failure"));


        MvcResult result = mockMvc.perform(post("/api/v1/resetPassword")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        verify(resetPasswordService).forgotPassword(email);

    }
    @Test
    public void testConfirmCode_Successful() {
        String confirmCode = "1234";
        String enteredConfirmCode = "1234";
        Pair<Boolean, String> response = Pair.of(true, "Confirmation was successful.");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(response.getSecond());

        when(resetPasswordService.confirmCode(confirmCode, enteredConfirmCode)).thenReturn(response);

        ResponseEntity<String> actualResponse = resetPasswordController.confirmCode(confirmCode, enteredConfirmCode);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testConfirmCode_IncorrectCode() {
        String confirmCode = "1234";
        String enteredConfirmCode = "5678";
        Pair<Boolean, String> response = Pair.of(false, "The entered confirmation code is not correct.");
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body(response.getSecond());

        when(resetPasswordService.confirmCode(confirmCode, enteredConfirmCode)).thenReturn(response);

        ResponseEntity<String> actualResponse = resetPasswordController.confirmCode(confirmCode, enteredConfirmCode);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testResetPassword_Successful() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "newPassword";
        Pair<Boolean, String> response = Pair.of(true, "Password reset was successful.");
        ResponseEntity<String> expectedResponse = ResponseEntity.ok(response.getSecond());

        when(resetPasswordService.resetPassword(email, newPassword, newPasswordRepeat)).thenReturn(response);

        ResponseEntity<String> actualResponse = resetPasswordController.resetPassword(email, newPassword, newPasswordRepeat);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testResetPassword_PasswordsNotMatch() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        String newPasswordRepeat = "differentPassword";
        Pair<Boolean, String> response = Pair.of(false, "Passwords do not match.");
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body(response.getSecond());

        when(resetPasswordService.resetPassword(email, newPassword, newPasswordRepeat)).thenReturn(response);

        ResponseEntity<String> actualResponse = resetPasswordController.resetPassword(email, newPassword, newPasswordRepeat);

        assertEquals(expectedResponse, actualResponse);
    }
}
