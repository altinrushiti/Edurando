package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.AddressRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class EditPasswordService {
    private final static String USER_NOT_FOUND = "User with Email %s was not found.";
    private final static String USER_NOT_FOUND_BY_ID = "User with id: %s was not found.";
    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private EditPasswordRequest pwRequest;

    public Pair<Boolean, String> editPassword(EditPasswordRequest pwRequest) {
        UserProfile user = userProfileRepository.findUserProfileById(pwRequest.getId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, pwRequest.getId())));

        String currentUserPw = user.getPassword();
        Pair<Boolean, String> newPwTuple = pwRequest.passwordTest(pwRequest.getNewPassword(), pwRequest.getNewPasswordRepeat());

        if (!bCryptPasswordEncoder.matches(pwRequest.getCurrentPassword(), currentUserPw)) {
            return Pair.of(false, "The current password entered does not match the user's current password.");
        }

        if (bCryptPasswordEncoder.matches(pwRequest.getNewPassword(), currentUserPw)) {
            return Pair.of(false, "Password could not be changed because the new password is the same as the previous password.");
        }

        if (!newPwTuple.getFirst()) {
            return newPwTuple;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(pwRequest.getNewPassword());
        user.setPassword(encodedPassword);
        userProfileRepository.save(user);

        return Pair.of(true, "Password changed successfully.");
    }
}
