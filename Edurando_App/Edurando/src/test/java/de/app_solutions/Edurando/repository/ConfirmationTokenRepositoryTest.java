package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.UserProfile;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
@SpringBootTest
@RunWith(SpringRunner.class)
class ConfirmationTokenRepositoryTest {


    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void findByToken() {
        String email = "max.mustermann@example.com";

        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Mustermann",
                email,
                "password");

        userProfileRepository.save(userProfile);

        ConfirmationToken token = new ConfirmationToken("1234567890abcdef", LocalDateTime.now(), LocalDateTime.now().plusHours(1), userProfile);
        confirmationTokenRepository.save(token);


        Optional<ConfirmationToken> foundToken = confirmationTokenRepository.findByToken("1234567890abcdef");

        assertThat(foundToken.isPresent()).isTrue();
       // assertThat(foundToken.get().getToken()).isEqualTo("1234567890abcdef");
        assertThat(foundToken.get().getUser().getUsername()).isEqualTo("max.mustermann@example.com");


    }


}

 */