package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.AddressRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Data
public class UserProfileService implements UserDetailsService {

    protected final static String USER_NOT_FOUND = "User with Email %s was not found.";
    protected final static String USER_NOT_FOUND_BY_ID = "User with id: %s was not found.";
    protected final static String UPLOAD_DIR  = "../edurando-frontend/src/assets/profilePictures/";
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

    public Pair<Boolean, String> uploadProfilePicture(Long id, MultipartFile file) throws IOException {
        Optional<UserProfile> user = userProfileRepository.findUserProfileById(id);
        if (user.isEmpty()) {
            return Pair.of(false, String.format(USER_NOT_FOUND_BY_ID, id));
        }
        String currentWorkingDirectory = System.getProperty("user.dir");
        File sourceFolder = new File(currentWorkingDirectory);
        String absolutePath = sourceFolder.getAbsolutePath();

        String folderPath = absolutePath + "/" + UPLOAD_DIR + id;

        // Erstelle das Verzeichnis, falls es nicht existiert
        File folder = new File(folderPath);
        boolean folderCreated = folder.mkdirs();
        if (!folderCreated && !folder.exists()) {
            return Pair.of(false, "Fehler beim Erstellen des Ordners!");
        }

        // Überprüfe, ob der Dateiname leer oder null ist
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.isEmpty()) {
            return Pair.of(false, "Ungültiger Dateiname");
        }

        try {
            // Speichere die Datei im Zielordner
            byte[] image = file.getBytes();
            Path path = Paths.get(folder.getAbsolutePath() + "/" + fileName);
            if (Objects.requireNonNull(folder.list()).length > 0) {
                boolean deleted = Objects.requireNonNull(folder.listFiles())[0].delete();
            }
            Files.write(path, image);
            user.get().setProfilePictureReference(String.format("../../../assets/profilePictures/%s/%s", id, fileName));
            userProfileRepository.save(user.get());
            return Pair.of(true, path.toString());
        } catch (IOException e) {
            return Pair.of(false, "Fehler beim Speichern der Datei");
        }
    }
    public List<UserProfile> search(String searchTerm) {
        return userProfileRepository.search(searchTerm);
    }

    public Pair<Boolean, String> removeImage(Long id) {
        Optional<UserProfile> user = userProfileRepository.findUserProfileById(id);
        if (user.isEmpty()) {
            return Pair.of(false, String.format(USER_NOT_FOUND_BY_ID, id));
        }

        FileSystemUtils.deleteRecursively(new File(UPLOAD_DIR + id));
        user.get().setProfilePictureReference("../../../assets/p_placeholder.png");
        userProfileRepository.save(user.get());

        return Pair.of(true, "Delete successfully");
    }
}
