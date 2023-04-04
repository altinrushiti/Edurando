package de.app_dev_solutions.edurando.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate email
        return s.contains("stud.th-luebeck.de");
    }
}
