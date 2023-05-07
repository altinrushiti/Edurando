package de.app_solutions.Edurando.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class PasswordValidator {


    public Pair<Boolean, String> passwordTest(String s1, String s2) {
        int pw_count = 0;
        String finalMsg;
        Pair<Boolean, String> tuple;
        StringBuilder sb = new StringBuilder();

        if (!s1.equals(s2)) {
            sb.append("Passwords do not match,");
            pw_count++;
        }
        if (!(s1.length() >= 8)) {
            sb.append("Password needs minimum length of 8,");
            pw_count++;
        }
        if (!(s1.chars().anyMatch(Character::isUpperCase) && s1.chars().anyMatch(Character::isLowerCase))) {
            sb.append("Password needs at least 1 upper and 1 lower case character,");
            pw_count++;
        }
        if (s1.chars().noneMatch(Character::isDigit)) {
            sb.append("Password needs at least 1 digit,");
            pw_count++;
        }
        if (s1.chars().anyMatch(Character::isLetterOrDigit)) {
            sb.append("Password needs at least 1 special character,");
            pw_count++;
        }
        if (pw_count > 0) {
            finalMsg = sb.toString();
            tuple = Pair.of(false, finalMsg.substring(0, finalMsg.length() - 1));

        } else {
            tuple = Pair.of(true, "Password is valid");
        }
        return tuple;

    }
}


