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

    protected final static String USER_NOT_FOUND = "User with Email %s was not found.";
    protected final static String USER_NOT_FOUND_BY_ID = "User with id: %s was not found.";
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserProfileDTOMapper userProfileDTOMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private EditPasswordRequest pwRequest;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userProfileRepository.findUserProfileByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(UserProfile user) {
        boolean userExists = userProfileRepository.findUserProfileByUsername(user.getUsername()).isPresent();

        if (userExists && user.isEnabled()) {
            throw new IllegalStateException("E-Mail already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        //userProfileRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        //user.setConfirmationToken(confirmationToken);

        // Hinzufügen des ConfirmationToken zum UserProfile
        //user.getConfirmationToken().add(confirmationToken);

         // Speichern Sie das aktualisierte UserProfile mit dem ConfirmationToken
        userProfileRepository.save(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }


    public int enableAppUser(String email) {
        return userProfileRepository.enableAppUser(email);
    }




    public List<UserProfileDTO> getAllUsers() {
        return userProfileRepository.findAll()
                .stream()
                .map(userProfileDTOMapper)
                .toList();
    }

    public UserProfileDTO getUserById(Long id) {
        return userProfileDTOMapper.apply(userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))));
    }

    public UserProfileDTO getUserByEmail(String email) {
        return userProfileDTOMapper.apply(userProfileRepository.findUserProfileByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email))));
    }

    public List<UserProfileDTO> showTopUsers() {
        return userProfileRepository.findTop10ByOrderByRatingDesc()
                .stream()
                .map(userProfileDTOMapper)
                .toList();
    }

}
