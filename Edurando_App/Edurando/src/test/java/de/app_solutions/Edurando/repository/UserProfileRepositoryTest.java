package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.TestApplicationConfig;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = TestApplicationConfig.class)
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;


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
