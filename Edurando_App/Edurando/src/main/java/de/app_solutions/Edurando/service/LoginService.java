package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Pair<Boolean, String> login(LoginRequest loginRequest) {

        boolean userExist = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).isPresent();
        if (!userExist) {
            return Pair.of(false, "Invalid credentials. Try again!");
        } else {
            UserProfile user = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).get();
            if (!user.isEnabled()) {
                return Pair.of(false, "You are not verified.");
            }
            if (user.isLocked()) {
                return Pair.of(false, "Your account is locked. Please contact the support.");
            }
            if (!(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()) && loginRequest.getEmail().equalsIgnoreCase(user.getUsername()))) {
                return Pair.of(false, "Invalid credentials. Try again!");
            }
            return Pair.of(true, "Login successful.");
        }
    }

}

