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
import org.springframework.data.util.Pair;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class EdurandoApplication {


    public static void main(String[] args) {
        SpringApplication.run(EdurandoApplication.class, args);
    }


    public Pair<List<Subject>, List<Topic>> randomSubjectTopic() {
        Random random = new Random();
        List<UserProfile> userProfiles = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        List<Topic> topics = new ArrayList<>();
        Pair<List<Subject>, List<Topic>> result =  Pair.of(subjects,topics);

        int indexOfArray;

        String[] subjectsArray = {"Maths", "Physics", "Chemistry", "Biology", "History","Programming"};
        String[] topicsArray = {"Algebra", "Mechanics", "Organic Chemistry", "Genetics", "World War II","C"};


        for (int i = 0; i < 7; i++) {

            indexOfArray = random.nextInt(subjectsArray.length);
            String randomSubjectName = subjectsArray[indexOfArray];
            String randomTopicName = topicsArray[indexOfArray];

            Subject s = new Subject();

            s.setName(randomSubjectName);
            s.setUserProfiles(userProfiles);
            s.setTopics(new ArrayList<>());
            subjects.add(s);

            Topic t = new Topic();

            t.setName(randomTopicName);
            t.setUserProfiles(userProfiles);
            t.setSubject(s);
            topics.add(t);


            s.getTopics().add(t);

            if (topics.size()== 2){
                break;
            }

        }


        return result;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserProfileService userProfileService) {
        return args -> {
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra1@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra2@stud.th-luebeck.de", "Test_123", true));
            userProfileService.signUpUser(new UserProfile("Student", "Krish", "Kalra", "krish.kalra3@stud.th-luebeck.de", "Test_123", true));
            //userProfileService.signUpUser(new UserProfile("Teacher", "Krish", "Kalra", "krish.kalra@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 5.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Altin", "Rushiti", "altin.rushiti@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Zainoul", "Barry", "zainoul.barry@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Bennet", "Gurklies", "bennet.gurklies@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Ahmed", "Radwan", "ahmed.radwan@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Ghamdan", "Al-Sofey", "ghamdan.al-sofeygg@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Sena", "Demircik", "sena.demircik@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 5.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "John", "Smith", "John.Smith@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Jane", "Johnson", "Jane.Johnson@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Michael", "Williams", "Michael.Williams@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 3.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "Emily", "Jones", "Emily.Jones@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 5.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
            userProfileService.signUpUser(new UserProfile("Teacher", "William", "Brown", "William.Brown@stud.th-luebeck.de", "Test_123", true, randomSubjectTopic().getFirst(), randomSubjectTopic().getSecond(), 4.0f, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"));
        };
    }
}

