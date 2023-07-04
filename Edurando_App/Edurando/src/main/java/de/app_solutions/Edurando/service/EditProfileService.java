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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.app_solutions.Edurando.service.UserProfileService.USER_NOT_FOUND_BY_ID;

@Service
@Data
public class EditProfileService {
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private EditPasswordRequest pwRequest;



    public Pair<Boolean, String> editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        UserProfile user = userProfileRepository.findUserProfileById(editPersonalDataRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, editPersonalDataRequest.getId())));

        List<String> errors = validateFields(editPersonalDataRequest);

        if (!errors.isEmpty()) {
            return Pair.of(false, String.join(",", errors));
        }

        user.setFirstName(editPersonalDataRequest.getFirstName());
        user.setLastName(editPersonalDataRequest.getLastName());
        user.setGender(editPersonalDataRequest.getGender());
        user.setRole(editPersonalDataRequest.getRole().equalsIgnoreCase("Student") ? Role.student : Role.teacher);
        user.setPersonalBiography(editPersonalDataRequest.getPersonalBiography());
        user.setMobile(editPersonalDataRequest.getMobile());

        Address address = user.getAddress();
        address.setStreet(editPersonalDataRequest.getStreet());
        address.setHouseNumber(editPersonalDataRequest.getHouseNumber());
        address.setCity(editPersonalDataRequest.getCity());
        address.setState(editPersonalDataRequest.getState());
        address.setPostCode(editPersonalDataRequest.getPostCode().isBlank() ? -1 : Integer.parseInt(editPersonalDataRequest.getPostCode()));

        user.setAddress(address);
        userProfileRepository.save(user);

        return Pair.of(true, "The processing was successful.");
    }

    private List<String> validateFields(EditPersonalDataRequest editPersonalDataRequest) {
        List<String> errors = new ArrayList<>();

        validateField(editPersonalDataRequest.getFirstName(), "First name", errors);
        validateField(editPersonalDataRequest.getLastName(), "Last name", errors);
        validateField(editPersonalDataRequest.getRole(), "Role", errors);

        if (Optional.ofNullable(editPersonalDataRequest.getStreet()).isEmpty() ||
                Optional.ofNullable(editPersonalDataRequest.getHouseNumber()).isEmpty() ||
                Optional.ofNullable(editPersonalDataRequest.getCity()).isEmpty() ||
                Optional.ofNullable(editPersonalDataRequest.getState()).isEmpty() ||
                Optional.ofNullable(editPersonalDataRequest.getPostCode()).isEmpty()) {
            errors.add("Please fill out all address fields or no address fields");
        }

        return errors;
    }

    private void validateField(String fieldValue, String fieldName, List<String> errors) {
        if (fieldValue.isBlank()) {
            errors.add(fieldName + " cannot be empty");
        }
    }

}
