package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserProfileRepositoryTest extends PostgresContainer {

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }
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
