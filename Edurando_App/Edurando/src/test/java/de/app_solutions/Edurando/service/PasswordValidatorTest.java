package de.app_solutions.Edurando.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import de.app_solutions.Edurando.service.PasswordValidator;

import static org.junit.Assert.*;
@SpringBootTest
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void testMatchTest() {
        assertTrue(passwordValidator.matchTest("password", "password"));
        assertFalse(passwordValidator.matchTest("password", "anotherPassword"));
    }

    @Test
    public void testLengthTest() {
        assertTrue(passwordValidator.lengthTest("password123"));
        assertFalse(passwordValidator.lengthTest("pass"));
    }

    @Test
    public void testUpperLowerCaseTest() {
        assertTrue(passwordValidator.upperLowerCaseTest("Password123"));
        assertFalse(passwordValidator.upperLowerCaseTest("password123"));
        assertFalse(passwordValidator.upperLowerCaseTest("PASSWORD123"));
    }

    @Test
    public void testDigitTest() {
        assertTrue(passwordValidator.digitTest("password123"));
        assertFalse(passwordValidator.digitTest("password"));
    }

    @Test
    public void testSpecialCharTest() {
        assertTrue(passwordValidator.specialCharTest("password!@#"));
        assertFalse(passwordValidator.specialCharTest("password123"));
    }
}

