package de.app_solutions.Edurando.service;

import org.springframework.stereotype.Service;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class PasswordValidator {



    public boolean matchTest(String s1, String s2) {
        return s1.equals(s2);
    }

    public boolean lengthTest(String s) {
        return s.length() >= 8;
    }

    public boolean upperLowerCaseTest(String s) {
        return s.chars().anyMatch(Character::isUpperCase) && s.chars().anyMatch(Character::isLowerCase);
    }

    public boolean digitTest(String s) {
        return s.chars().anyMatch(Character::isDigit);
    }
    public boolean specialCharTest(String s) {
        return s.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }
}