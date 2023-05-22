package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.AddressRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class EditProfileService {

    private final static String USER_NOT_FOUND = "User with Email %s was not found.";
    private final static String USER_NOT_FOUND_BY_ID = "User with id: %s was not found.";
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private EditPasswordRequest pwRequest;

    public Pair<Boolean, List<String>> editPassword(EditPasswordRequest pwRequest) {
        UserProfile user = userProfileRepository.findUserProfileById(pwRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, pwRequest.getId())));

        String currentUserPw = user.getPassword();
        Pair<Boolean, List<String>> newPwTuple = pwRequest.passwordTest(pwRequest.getNewPassword(), pwRequest.getNewPasswordRepeat());

        if (!bCryptPasswordEncoder.matches(pwRequest.getCurrentPassword(), currentUserPw)) {
            return Pair.of(false, List.of("The current password entered does not match the user's current password."));
        }

        if (bCryptPasswordEncoder.matches(pwRequest.getNewPassword(), currentUserPw)) {
            return Pair.of(false, List.of("Password could not be changed because the new password is the same as the previous password."));
        }

        if (!newPwTuple.getFirst()) {
            return newPwTuple;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(pwRequest.getNewPassword());
        user.setPassword(encodedPassword);
        userProfileRepository.save(user);

        return Pair.of(true, List.of("Password changed successfully."));
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
