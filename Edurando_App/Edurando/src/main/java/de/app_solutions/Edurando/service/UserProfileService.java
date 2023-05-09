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
    private final static String USER_NOT_FOUND_BY_ID = "user not found: %s";
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

    public Pair<Boolean, String> editPassword(EditPasswordRequest pwRequest) {
        UserProfile user = userProfileRepository.findUserProfileById(pwRequest.getId()).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, pwRequest.getId())));
        String currentUserPw = user.getPassword();

        if (!bCryptPasswordEncoder.matches(pwRequest.getCurrentPassword(), currentUserPw)) {
            Pair<Boolean, String> tuple = Pair.of(false, "Das eingegebene aktuelle Passwort entspricht nicht dem aktuellen Nutzerpasswort.");
            System.err.println(tuple);
            return tuple;
        }

        if (bCryptPasswordEncoder.matches(pwRequest.getNewPassword(), currentUserPw)) {
            Pair<Boolean, String> tuple = Pair.of(false, "Passwort konnte nicht geändert werden, " +
                    "da Ihre Eingabe mit dem Ihren vorigen Passwort übereinstimmt.");
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

        return Pair.of(true, "Passwort erfolgreich geändert.");
}

    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public String editPersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        return "";
    }
}
