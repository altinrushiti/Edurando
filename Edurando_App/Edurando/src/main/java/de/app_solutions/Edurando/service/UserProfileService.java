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

    public UserProfile getUserByEmail(String email) {
        return userProfileRepository.findUserProfileByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public Pair<Boolean, String> editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {

        UserProfile user = userProfileRepository.findUserProfileById(editPersonalDataRequest.getId()).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, editPersonalDataRequest.getId())));
        Address address = user.getAddress();
        StringBuilder sb = new StringBuilder();
        if (editPersonalDataRequest.getFirstName().isBlank()) sb.append("First name cannot be empty,");
        if (editPersonalDataRequest.getLastName().isBlank()) sb.append("Last name cannot be empty,");
        if (editPersonalDataRequest.getRole().isBlank()) sb.append("Role cannot be empty,");
        if ((editPersonalDataRequest.getStreet().isBlank() || editPersonalDataRequest.getHouseNumber().isBlank() || editPersonalDataRequest.getCity().isBlank() || editPersonalDataRequest.getState().isBlank() || editPersonalDataRequest.getPostCode().isBlank()) && !(editPersonalDataRequest.getStreet().isBlank() && editPersonalDataRequest.getHouseNumber().isBlank() && editPersonalDataRequest.getCity().isBlank() && editPersonalDataRequest.getState().isBlank() && editPersonalDataRequest.getPostCode().isBlank())) {
            sb.append("Please fill out all address fields or no address fields,");
        } else if (!(editPersonalDataRequest.getFirstName().isBlank() || editPersonalDataRequest.getLastName().isBlank())) {
            user.setFirstName(editPersonalDataRequest.getFirstName());
            user.setLastName(editPersonalDataRequest.getLastName());
            user.setGender(editPersonalDataRequest.getGender());
            user.setRole(editPersonalDataRequest.getRole().equals("Student") ? Role.student : Role.teacher);
            user.setPersonalBiography(editPersonalDataRequest.getPersonalBiography());
            user.setMobile(editPersonalDataRequest.getMobile());
            user.setProfilePictureReference(editPersonalDataRequest.getProfilePictureReference());
            address.setStreet(editPersonalDataRequest.getStreet());
            address.setHouseNumber(editPersonalDataRequest.getHouseNumber());
            address.setCity(editPersonalDataRequest.getCity());
            address.setState(editPersonalDataRequest.getState());
            address.setPostCode(editPersonalDataRequest.getPostCode().isBlank() ? -1 : Integer.parseInt(editPersonalDataRequest.getPostCode()));
            user.setAddress(address);
            userProfileRepository.save(user);
            return Pair.of(true, "The processing was successful");
        }
        return Pair.of(false, sb.toString());
    }

    public List<UserProfile> showTopUsers() {
        return userProfileRepository.findTop10ByOrderByRatingDesc();
    }

}
