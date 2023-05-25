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

@Service
@AllArgsConstructor
public class LoginService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final JwtTokenProvider jwtTokenProvider;
    private final static String USER_NOT_FOUND = "User with Email %s was not found.";

    public Pair<Boolean, String> login(LoginRequest loginRequest) {

        boolean userExist = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).isPresent();
        List<String> l = new ArrayList<>();
        if (!userExist) {
            return Pair.of(false, "Password or username is not correct.");
        } else {
            boolean rs = true;
            UserProfile user = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).get();
            if (!user.isEnabled()) {
                return Pair.of(false, "You are not verified.");
            }
            if (user.isLocked()) {
                return Pair.of(false, "Your account is locked.");
            }
            if (!(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()) && loginRequest.getEmail().equals(user.getUsername()))) {
                return Pair.of(false, "Password or username is not correct.");
            }
            return Pair.of(true, "Successful!");
        }

    }
}
