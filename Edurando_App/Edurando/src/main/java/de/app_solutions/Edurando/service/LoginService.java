package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND = "User with Email %s was not found.";

    public Pair<Boolean,String> login(LoginRequest loginRequest) {

        UserProfile user = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, loginRequest.getEmail())));

        if (!user.isEnabled()) {
            // Benutzerkonto ist deaktiviert
            return Pair.of(false,"User isn't verified.");
        }
        if (user.isLocked()) {
            // Benutzerkonto ist deaktiviert
            return Pair.of(false,"User's account is locked.");
        }



        if (!(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()) || loginRequest.getEmail().equals(user.getUsername()))) {
            // Falsches Passwort
            return Pair.of(false,"Password or username is not correct.");
        }

        // Erfolgreicher Login
        return Pair.of(true, "Login was successful.");
    }

}
