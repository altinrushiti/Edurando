package de.app_solutions.Edurando;

import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdurandoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EdurandoApplication.class, args);
	}

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public void run(String... args) throws Exception {
        UserProfile  user1 = UserProfile.builder().id(1L).firstname("Krish").lastname("Kalra").personalBiography("").profilePictureReference("").rating(5.0F).email("krish.kalra3@gmail.com").role(Role.admin).password("Test").build();
        userProfileRepository.save(user1);
    }
}
