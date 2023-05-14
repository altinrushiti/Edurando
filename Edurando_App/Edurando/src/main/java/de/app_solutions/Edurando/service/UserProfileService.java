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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class UserProfileService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final static String USER_NOT_FOUND_BY_ID = "user not found: %s";
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

    public Pair<Boolean, String> editPassword(EditPasswordRequest pwRequest) {
        UserProfile user = userProfileRepository.findUserProfileById(pwRequest.getId()).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, pwRequest.getId())));
        String currentUserPw = user.getPassword();

        if (!bCryptPasswordEncoder.matches(pwRequest.getCurrentPassword(), currentUserPw)) {
            Pair<Boolean, String> tuple = Pair.of(false, "The current password entered does not match the current user password.");
            System.err.println(tuple);
            return tuple;
        }

        if (bCryptPasswordEncoder.matches(pwRequest.getNewPassword(), currentUserPw)) {
            Pair<Boolean, String> tuple = Pair.of(false, "Password could not be changed, because the password you entered is the same as your previous password.");
            System.err.println(tuple);
            return tuple;
        }

        Pair<Boolean, String> newPwTuple = pwRequest.passwordTest(pwRequest.getNewPassword(), pwRequest.getNewPasswordRepeat());

        if (!newPwTuple.getFirst()) {
            System.err.println(newPwTuple);
            return newPwTuple;
        }
        // Setzen Sie das neue Passwort
        String encodedPassword = bCryptPasswordEncoder.encode(pwRequest.getNewPassword());
        user.setPassword(encodedPassword);


        // Speichern Sie die aktualisierten Nutzerdaten in der Datenbank
        userProfileRepository.save(user);

        return Pair.of(true, "Password changed successfully.");
}

    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserById(Long id) {
        return userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
    }

    public String editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        Address address;
        Optional<Address> addressN = addressRepository.findAddressByStreetAndHouseNumberAndCityAndPostCodeAndState(editPersonalDataRequest.getStreet(),
                                                                                                                editPersonalDataRequest.getHouseNumber(),
                                                                                                                editPersonalDataRequest.getCity(),
                                                                                                                editPersonalDataRequest.getPostCode(),
                                                                                                                editPersonalDataRequest.getState());
        UserProfile user = userProfileRepository.findById(editPersonalDataRequest.getId()).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found: %s", editPersonalDataRequest.getId())));
        if (!editPersonalDataRequest.getFirstName().trim().equals("")) user.setFirstName(editPersonalDataRequest.getFirstName());
        if (!editPersonalDataRequest.getLastName().trim().equals("")) user.setLastName(editPersonalDataRequest.getLastName());
        if (!editPersonalDataRequest.getGender().trim().equals("")) user.setGender(editPersonalDataRequest.getGender());
        if (!editPersonalDataRequest.getRole().trim().equals("")) user.setRole(editPersonalDataRequest.getRole().equals("Student") ? Role.student : Role.teacher);
        if (!editPersonalDataRequest.getPersonalBiography().trim().equals("")) user.setPersonalBiography(editPersonalDataRequest.getPersonalBiography());
        if (!editPersonalDataRequest.getMobile().trim().equals("")) user.setMobile(editPersonalDataRequest.getMobile());
        if (!editPersonalDataRequest.getProfilePictureReference().trim().equals("")) user.setProfilePictureReference(editPersonalDataRequest.getProfilePictureReference());
        if (addressN.isEmpty()) {
            List<UserProfile> users = List.of(user);
            address = new Address(editPersonalDataRequest.getStreet(),
                    editPersonalDataRequest.getHouseNumber(),
                    editPersonalDataRequest.getCity(),
                    editPersonalDataRequest.getPostCode(),
                    editPersonalDataRequest.getState(),
                    users);
        } else {
            address = addressN.get();
            if (!editPersonalDataRequest.getStreet().trim().equals(""))
                address.setStreet(editPersonalDataRequest.getStreet());
            if (!editPersonalDataRequest.getHouseNumber().trim().equals(""))
                address.setHouseNumber(editPersonalDataRequest.getHouseNumber());
            if (!editPersonalDataRequest.getCity().trim().equals(""))
                address.setCity(editPersonalDataRequest.getCity());
            if (!editPersonalDataRequest.getState().trim().equals(""))
                address.setState(editPersonalDataRequest.getState());
            if (editPersonalDataRequest.getPostCode() != -1)
                address.setPostCode(editPersonalDataRequest.getPostCode());
        }
        addressRepository.save(address);
        user.setAddress(address);
        userProfileRepository.save(user);

        return "";
    }
}
