package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.AddressRepository;
import de.app_solutions.Edurando.repository.ConfirmationTokenRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static de.app_solutions.Edurando.service.UserProfileService.UPLOAD_DIR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EditPasswordRequest editPasswordRequest;

    @Autowired
    private SubjectService subjectService;

    @Test
    public void signUpUserTest() throws Exception {
        String token1 = userProfileService.signUpUser(new UserProfile("Student", "Firstname", "Lastname", "email2@stud.th-luebeck.de", "Password_123"));

        String token2 = confirmationTokenRepository.findAll().get(confirmationTokenRepository.findAll().toArray().length - 1).getToken();

        assertEquals(token1, token2);
    }



    @Test
    public void enableAppUser() throws Exception {
        userProfileService.signUpUser(new UserProfile("Student", "Firstname", "Lastname", "email@stud.th-luebeck.de", "Password_123"));
        int enable = userProfileService.enableAppUser("email@stud.th-luebeck.de");
        boolean exists = userProfileRepository.findUserProfileByUsername("email@stud.th-luebeck.de").isPresent();
        boolean enabled = false;
        if (exists) {
            enabled = userProfileRepository.findUserProfileByUsername("email@stud.th-luebeck.de").get().isEnabled();
        }

        // Assert the enable value and enabled status
        assertEquals(1, enable);
        assertTrue(enabled);
    }


    @Test
    @Transactional
    void search() {
        // Arrange
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setId(1L);
        userProfile1.setRole(Role.teacher);
        userProfile1.setFirstName("Max");
        userProfile1.setLastName("Mustermann");
        userProfile1.setUsername("max.mustermann@example.com");

        userProfile1.setSubjects(new ArrayList<>());
        userProfile1.setTopics(new ArrayList<>());

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setId(1L);
        editSubjectRequest.setSubject("Physics");
        editSubjectRequest.setTopic("Thermodynamics");
        userProfileService.signUpUser(userProfile1);
        subjectService.addSubjectData(editSubjectRequest);


        String searchTerm = "Physics";

        // Act
        List<UserProfile> searchResults = userProfileRepository.search(searchTerm);

        // Assert
        assertThat(searchResults.get(0).getFirstName()).isEqualTo("Max");
        assertThat(searchResults.get(0).getLastName()).isEqualTo("Mustermann");
        assertThat(searchResults.get(0).getUsername()).isEqualTo("max.mustermann@example.com");

    }

    /*@Test
    public void uploadFileAndRemoveFileTest() throws Exception {
        // Arrange
        UserProfile user = new UserProfile();
        user.setId(1L);
        user.setRole(Role.teacher);
        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setUsername("max.mustermann@example.com");

        userProfileService.signUpUser(user);

        // Erstelle den Inhalt der Datei
        byte[] fileContent = "Inhalt der Datei".getBytes(StandardCharsets.UTF_8);

        // Erstelle den MockMultipartFile
        MultipartFile file = new MockMultipartFile("file", "testfile.txt", MediaType.TEXT_PLAIN_VALUE, fileContent);

        Pair<Boolean, String> result1 = userProfileService.uploadProfilePicture(user.getId(), file);
        String currentWorkingDirectory = System.getProperty("user.dir");
        File sourceFolder = new File(currentWorkingDirectory);
        String absolutePath = sourceFolder.getAbsolutePath();
        String folderPath = absolutePath + "/" + UPLOAD_DIR + user.getId() + "/" + file.getOriginalFilename();

        Pair<Boolean, String> result2 = userProfileService.removeImage(user.getId());

        assertTrue(result1.getFirst());
        assertEquals(folderPath.replace("/", "\\"), result1.getSecond());

        assertTrue(result2.getFirst());
        assertEquals("Delete successfully", result2.getSecond());
    }*/
}
