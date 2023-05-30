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
        Optional<UserProfile> user = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail());

        if (user.isEmpty() || !(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()) && loginRequest.getEmail().equalsIgnoreCase(user.get().getUsername()))) {
            return Pair.of(false, "Invalid credentials. Try again!");
        }
        if (!user.get().isEnabled()) {
            return Pair.of(false, "You are not verified.");
        }
        if (user.get().isLocked()) {
            return Pair.of(false, "Your account is locked. Please contact the support.");
        }
        return Pair.of(true, "Login successful.");
    }
}

