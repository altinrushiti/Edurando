package de.app_solutions.Edurando;

import de.app_solutions.Edurando.config.security.PasswordEncoder;
import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.RegistrationService;
import de.app_solutions.Edurando.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

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
            userProfileService.signUpUser(new UserProfile("Teacher", "Krish", "Kalra", "krish.kalra4@stud.th-luebeck.de", "Test_123", true, 5.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Altin", "Rushiti", "krish.kalra5@stud.th-luebeck.de", "Test_123", true, 4.8f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Zainoul", "Barry", "krish.kalra6@stud.th-luebeck.de", "Test_123", true, 4.2f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Bennet", "Gurklies", "krish.kalra7@stud.th-luebeck.de", "Test_123", true, 4.6f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Ahmed", "Radwan", "krish.kalra8@stud.th-luebeck.de", "Test_123", true, 4.5f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Ghamdan", "Al-Sofey", "krish.kalra9@stud.th-luebeck.de", "Test_123", true, 4.2f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Sena", "Demircik", "krish.kalra10@stud.th-luebeck.de", "Test_123", true, 4.1f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "John", "Smith", "krish.kalra11@stud.th-luebeck.de", "Test_123", true, 3.6f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Jane", "Johnson", "krish.kalra12@stud.th-luebeck.de", "Test_123", true, 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Michael", "Williams", "krish.kalra13@stud.th-luebeck.de", "Test_123", true, 3.4f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Emily", "Jones", "krish.kalra14@stud.th-luebeck.de", "Test_123", true, 3.9f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "William", "Brown", "krish.kalra15@stud.th-luebeck.de", "Test_123", true, 3.1f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
        };
    }


}
