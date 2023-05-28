package de.app_solutions.Edurando.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PasswordValidator {


    public Pair<Boolean, String> passwordTest(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        String pwInvalid;
        String pwNoMatch;
        boolean rs = true;
        if (!(s1.length() >= 8)
                || !(s1.chars().anyMatch(Character::isUpperCase) && s1.chars().anyMatch(Character::isLowerCase))
                || s1.chars().noneMatch(Character::isDigit)
                || s1.matches("[a-zA-Z0-9]*")) {
            pwInvalid = "Please choose a more secure password, at least 8 characters long, known only to you, and difficult for others to guess.";
            sb.append(pwInvalid);
            rs = false;
        }
        if (!s1.equals(s2)) {
            pwNoMatch ="Passwords do not match, please enter your new password here again.";
            sb.append(pwNoMatch);
            rs = false;
        }
            return Pair.of(rs,sb.toString());
    }
}


