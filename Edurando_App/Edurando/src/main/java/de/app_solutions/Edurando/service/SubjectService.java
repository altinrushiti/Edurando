package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.repository.TopicRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private static final String USER_NOT_FOUND_BY_ID = "USER_NOT_FOUND_BY_ID ";
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TopicRepository topicRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectByName(String name) {

        return subjectRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }


    public String updateSubjectData(EditSubjectRequest editSubjectRequest) {
        // Listen von userProifles und  topics erstellen , um sie in Klasse subject zu speichern
        UserProfile user = userProfileRepository.findUserProfileById(editSubjectRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, editSubjectRequest.getUserId())));

        List<UserProfile> userProfilesBySubject = subjectRepository.findUserProfilesByName(editSubjectRequest.getSubject()).orElseGet(ArrayList::new);
        List<Topic> topicsBySubject = subjectRepository.findTopicsByName(editSubjectRequest.getSubject()).orElseGet(ArrayList::new);

        // Listen von subject  erstellen , um sie in Klasse userProifle zu speichern
        List<Subject> subjectsByUserProfile = user.getSubjects();
        List<Topic> topicsByUserProfile = user.getTopics();
        List<UserProfile> userProfilesByTopic = topicRepository.findUserProfilesByName(editSubjectRequest.getTopic()).orElseGet(ArrayList::new);

        // create Objects
        Subject subject = new Subject();
        Topic topic = new Topic();

        subject.setName(editSubjectRequest.getSubject());
        topic.setName(editSubjectRequest.getTopic());

        userProfilesByTopic.add(user);
        userProfilesBySubject.add(user);
        topicsBySubject.add(topic);
        topicsByUserProfile.add(topic);
        subjectsByUserProfile.add(subject);

        // add to sub
        subject.setUserProfiles(userProfilesBySubject);
        subject.setTopics(topicsBySubject);
        subjectRepository.save(subject);


        // add to Top
        topic.setUserProfiles(userProfilesByTopic);
        topic.setSubject(subject);
        topicRepository.save(topic);

        //add to us
        user.setSubjects(subjectsByUserProfile);
        user.setTopics(topicsBySubject);
        userProfileRepository.save(user);



        return "Subject is updated";

    }



}