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

    public Pair<Boolean, List<String>> editPassword(EditPasswordRequest pwRequest) {

        UserProfile user = userProfileRepository.findUserProfileById(pwRequest.getId()).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, pwRequest.getId())));
        List<String> l = new ArrayList<String>();
        String currentUserPw = user.getPassword();

        Pair<Boolean, List<String>> newPwTuple = pwRequest.passwordTest(pwRequest.getNewPassword(), pwRequest.getNewPasswordRepeat());

        if (!bCryptPasswordEncoder.matches(pwRequest.getCurrentPassword(), currentUserPw)) {

            Pair<Boolean, List<String>> tuple = Pair.of(false, List.of("The current password entered does not match the current user password."));
            System.err.println(tuple);
            return tuple;
        }

        if (bCryptPasswordEncoder.matches(pwRequest.getNewPassword(), currentUserPw)) {
            Pair<Boolean, List<String>> tuple = Pair.of(false, List.of("Password could not be changed, because the password you entered is the same as your previous password."));
            System.err.println(tuple);
            return tuple;
        }

        if (!newPwTuple.getFirst()) {
            System.err.println(newPwTuple);
            return newPwTuple;
        }
        // Setzen Sie das neue Passwort
        String encodedPassword = bCryptPasswordEncoder.encode(pwRequest.getNewPassword());
        user.setPassword(encodedPassword);


        // Speichern Sie die aktualisierten Nutzerdaten in der Datenbank
        userProfileRepository.save(user);

        return Pair.of(true, List.of("Password changed successfully."));
}

    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserById(Long id) {
        return userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
    }

    public Pair<Boolean, String> editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        /*Address address;
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
            address = user.getAddress();
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

        return "";*/

        UserProfile user = userProfileRepository.findUserProfileById(editPersonalDataRequest.getId()).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, editPersonalDataRequest.getId())));
        Address address = user.getAddress();
        StringBuilder sb = new StringBuilder();
        if (editPersonalDataRequest.getFirstName().isBlank()) sb.append("First name cannot be empty,");
        if (editPersonalDataRequest.getLastName().isBlank()) sb.append("Last name cannot be empty,");
        if (editPersonalDataRequest.getRole().isBlank()) sb.append("Role cannot be empty,");
        if ((editPersonalDataRequest.getStreet().isBlank() || editPersonalDataRequest.getHouseNumber().isBlank() || editPersonalDataRequest.getCity().isBlank() || editPersonalDataRequest.getState().isBlank() || editPersonalDataRequest.getPostCode().isBlank()) && !(editPersonalDataRequest.getStreet().isBlank() && editPersonalDataRequest.getHouseNumber().isBlank() && editPersonalDataRequest.getCity().isBlank() && editPersonalDataRequest.getState().isBlank() && editPersonalDataRequest.getPostCode().isBlank())) {
            sb.append("Please fill out all address fields or no address fields,");
        } else if (!(editPersonalDataRequest.getFirstName().trim().equals("") || editPersonalDataRequest.getLastName().trim().equals(""))) {
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
}
