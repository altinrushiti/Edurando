package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.AddressRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Data
public class UserProfileService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with Email %s was not found.";
    private final static String USER_NOT_FOUND_BY_ID = "User with id: %s was not found.";
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private EditPasswordRequest pwRequest;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userProfileRepository.findUserProfileByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(UserProfile user) {
        boolean userExists = userProfileRepository.findUserProfileByUsername(user.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("E-Mail already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userProfileRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return userProfileRepository.enableAppUser(email);
    }




    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserById(Long id) {
        return userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
    }


}
