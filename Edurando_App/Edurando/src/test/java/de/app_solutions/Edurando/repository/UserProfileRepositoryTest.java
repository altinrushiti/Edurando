package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.UserProfile;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
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
