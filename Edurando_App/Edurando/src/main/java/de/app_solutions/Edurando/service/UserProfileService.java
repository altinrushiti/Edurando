package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.model.UserProfile;
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
import java.util.List;
import java.util.UUID;

@Service
@Data
public class UserProfileService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final UserProfileRepository userProfileRepository;
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

    public Pair<Boolean, String> editPassword(UserProfile user) {

      /*  if (pwRequest.getCurrentPassword().equals(user.getPassword())) {
            Pair<Boolean, String> tuple = Pair.of(false, "Neues Passwort konnte nicht gesetzt werden, da es dem Vorigen entspricht.");
            System.err.println(tuple);
            return tuple;
        }
        if (pwRequest.matchTest(pwRequest.getCurrentPassword(), pwRequest.getCurrentPasswordRepeat()) &&
                pwRequest.lengthTest(pwRequest.getCurrentPassword()) &&
                pwRequest.upperLowerCaseTest(pwRequest.getCurrentPassword()) &&
                pwRequest.digitTest(pwRequest.getCurrentPassword()) &&
                pwRequest.specialCharTest(pwRequest.getCurrentPassword())) {


        }

        // Setzen Sie das neue Passwort
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        // Speichern Sie die aktualisierten Nutzerdaten in der Datenbank
        userRepository.save(user);
    }
*/
        return null;
    }

    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public String editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        return "";
    }
}
