package de.app_solutions.Edurando.service;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PasswordValidatorTest {

    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void passwordMatchFailTest() {
        assertEquals(Pair.of(false, "Passwords do not match, please enter your new password here again."),
                passwordValidator.passwordTest("Hello123!", "Hello1234!"));
    }

    @Test
    public void passwordLengthFailTest() {
        assertEquals(Pair.of(false, "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess."),
                passwordValidator.passwordTest("He_lo1", "He_lo1"));
    }

    @Test
    public void passwordUpperLowerCaseFailTest() {
        assertEquals(Pair.of(false, "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess."), passwordValidator.passwordTest("hello123!", "hello123!"));
    }

    @Test
    public void passwordDigitFailTest() {
        assertEquals(Pair.of(false, "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess."), passwordValidator.passwordTest("HelloWorld!", "HelloWorld!"));
    }

    @Test
    public void passwordSpecialCharFailTest() {
        assertEquals(Pair.of(false, "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess."), passwordValidator.passwordTest("HelloWorld123", "HelloWorld123"));
    }

    @Test
    public void passwordAllCriteriumFailTest() {
        assertEquals(Pair.of(false, "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess.Passwords do not match, please enter your new password here again."),
                passwordValidator.passwordTest("hell", "hello"));
    }

    @Test
    public void passwordAllCriteriumSuccessTest() {
        assertEquals(Pair.of(true, ""), passwordValidator.passwordTest("Hello_World123!", "Hello_World123!"));
    }

}

