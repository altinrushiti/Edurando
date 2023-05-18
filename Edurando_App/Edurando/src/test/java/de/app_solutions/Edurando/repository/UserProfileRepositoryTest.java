package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.service.UserProfileService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserProfileRepositoryTest {


    @Autowired
    private UserProfileRepository userProfileRepository;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void findUserProfileByUsername() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Musterfrau",
                email,
                "password");

        userProfileRepository.save(userProfile);
        // When
        Optional<UserProfile> userProfile1 = userProfileRepository.findUserProfileByUsername(email);

        //then
        assertTrue(userProfile1.isPresent());
        assertEquals(userProfile1.get().getUsername(), userProfile.getUsername());
        assertEquals(userProfile1.get().getPassword(), userProfile.getPassword());

    }




}
