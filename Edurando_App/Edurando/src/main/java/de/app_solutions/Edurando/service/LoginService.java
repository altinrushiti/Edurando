package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
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

    public Pair<Boolean, List<String>> login(LoginRequest loginRequest) {

        UserProfile user = userProfileRepository.findUserProfileByUsername(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, loginRequest.getEmail())));
        boolean rs = true;
        List<String> l = new ArrayList<>();
        if (!user.isEnabled()) {
            rs = false;
            l.add("User isn't verified.");
        }
        if (user.isLocked()) {
            rs = false;
            l.add("User's account is locked.");
        }
        if (!(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()) || loginRequest.getEmail().equals(user.getUsername()))) {
            rs = false;
            l.add("Password or username is not correct.");
        }

        if (rs) {
            return Pair.of(rs,List.of(String.valueOf(user.getId())));
        } else {
            return Pair.of(rs,l);
        }
    }
}
