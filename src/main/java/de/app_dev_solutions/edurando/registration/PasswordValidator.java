package de.app_dev_solutions.edurando.registration;

import org.springframework.stereotype.Service;

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
