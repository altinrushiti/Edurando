package de.app_solutions.Edurando;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.RegistrationService;
import de.app_solutions.Edurando.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class EdurandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdurandoApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(UserProfileService userProfileService) {
        return args -> {
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra2@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra3@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra4@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra5@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra6@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra7@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra8@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra9@stud.th-luebeck.de", "Test_123", true));
        };
    }

}
