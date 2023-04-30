package de.app_solutions.Edurando.service;

import org.springframework.stereotype.Service;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class PasswordValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.length() >= 8 &&
                s.chars().anyMatch(Character::isUpperCase) &&
                s.chars().anyMatch(Character::isLowerCase) &&
                s.chars().anyMatch(Character::isDigit) &&
                s.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }
}
