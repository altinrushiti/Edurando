package de.app_solutions.Edurando.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordValidatorTest {

    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void passwordMatchFailTest() {
        assertEquals(Pair.of(false, "Passwords do not match"), passwordValidator.passwordTest("Hello123!", "Hello1234!"));

    }

    @Test
    public void passwordLengthFailTest() {
        assertEquals(Pair.of(false, "Password needs minimum length of 8"), passwordValidator.passwordTest("He_lo1", "He_lo1"));

    }

    @Test
    public void passwordUpperLowerCaseFailTest() {
        assertEquals(Pair.of(false, "Password needs at least 1 upper and 1 lower case character"), passwordValidator.passwordTest("hello123!", "hello123!"));

    }

    @Test
    public void passwordDigitFailTest() {
        assertEquals(Pair.of(false, "Password needs at least 1 digit"), passwordValidator.passwordTest("HelloWorld!", "HelloWorld!"));

    }

    @Test
    public void passwordSpecialCharFailTest() {
        assertEquals(Pair.of(false, "Password needs at least 1 special character"), passwordValidator.passwordTest("HelloWorld123", "HelloWorld123"));
    }
    @Test
    public void passwordAllCriteriumFailTest() {
        assertEquals(Pair.of(false, "Passwords do not match,Password needs minimum length of 8,Password needs at least 1 upper and 1 lower case character,Password needs at least 1 digit,Password needs at least 1 special character"), passwordValidator.passwordTest("hell", "hello"));
    }
    @Test
    public void passwordAllCriteriumSuccessTest() {
        assertEquals(Pair.of(true, "Password is valid"), passwordValidator.passwordTest("Hello_World123!", "Hello_World123!"));
    }

}

