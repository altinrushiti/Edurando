package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;


    @Test
    void findUserProfileByUsername() {
        String email = "max.mustermann@example.com";
        // given
        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Mustermann",
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
