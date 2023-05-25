package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.repository.TopicRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void saveSubjectAndTopic(EditSubjectRequest editSubjectRequest, UserProfile userProfile) {
        String subjectName = ((String.valueOf(editSubjectRequest.getSubject().charAt(0))).toUpperCase() + editSubjectRequest.getSubject().substring(1)).trim();
        String topicName = ((String.valueOf(editSubjectRequest.getTopic().charAt(0))).toUpperCase() + editSubjectRequest.getTopic().substring(1)).trim();


        List<UserProfile> userProfiles = List.of(userProfile);

        List<Subject> subjects = userProfile.getSubjects();
        List<Topic> topics = userProfile.getTopics();

        Subject s = new Subject();
        subjectRepository.save(s);
        s.setName(subjectName);
        s.setUserProfiles(userProfiles);
        s.setTopics(new ArrayList<>()); // Neue leere Liste für jedes Subject erstellen
        subjects.add(s);

        Topic t = new Topic();
        topicRepository.save(t);
        t.setName(topicName);
        t.setUserProfiles(userProfiles);
        t.setSubject(s); // Jedes Topic einer spezifischen Subject-Instanz zuordnen
        topics.add(t);

        s.getTopics().add(t); // Das Topic zur Liste der Topics des Subjects hinzufügen


        userProfiles.get(0).setSubjects(subjects);
        userProfiles.get(0).setTopics(topics);

        userProfileRepository.saveAllAndFlush(userProfiles);

    }

    public void saveTopic(EditSubjectRequest editSubjectRequest, UserProfile userProfile) {
        String subjectName = ((String.valueOf(editSubjectRequest.getSubject().charAt(0))).toUpperCase() + editSubjectRequest.getSubject().substring(1)).trim();
        String topicName = ((String.valueOf(editSubjectRequest.getTopic().charAt(0))).toUpperCase() + editSubjectRequest.getTopic().substring(1)).trim();

        List<UserProfile> userProfiles = List.of(userProfile);
        List<Topic> topics = userProfile.getTopics();

        Topic t = new Topic();
        topicRepository.save(t);
        t.setName(topicName);
        t.setUserProfiles(userProfiles);
        t.setSubject(subjectRepository.findByName(subjectName).get());
        topics.add(t);

        userProfiles.get(0).setTopics(topics);

        userProfileRepository.saveAll(userProfiles);
    }

    public Pair<Boolean, String> addSubjectData(EditSubjectRequest editSubjectRequest) {

        // call userprofile

        UserProfile userProfile = userProfileRepository
                .findUserProfileById(editSubjectRequest.getId()).orElseThrow(() -> new UsernameNotFoundException
                        (String.format(USER_NOT_FOUND_BY_ID, editSubjectRequest.getId())));

        if (editSubjectRequest.getSubject().isEmpty() || editSubjectRequest.getTopic().isEmpty()) {

            return Pair.of(false, "Please fill out all fields");
        }

        List<String> subjectsInUser = userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getId());
        List<String> topicsInUser = userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getId());

        boolean subIsExist = false;
        boolean topIsExist = false;
        if (subjectsInUser.isEmpty()) {
            saveSubjectAndTopic(editSubjectRequest, userProfile);
        } else {


            for (String subjectInUser : subjectsInUser) {

                if (subjectInUser.toLowerCase().contains(editSubjectRequest.getSubject().toLowerCase())) {

                    System.out.println(subjectInUser);
                    subIsExist = true;

                }

            }
            for (String topicInUser : topicsInUser) {
                if (topicInUser.toLowerCase().contains(editSubjectRequest.getTopic().toLowerCase())) {

                    System.out.println(topicInUser);
                    topIsExist = true;

                }

            }

            if(subIsExist && topIsExist){
                return Pair.of(false, "Subject and topic are already exist");

            }
            else if (subIsExist){
                saveTopic(editSubjectRequest, userProfile);
            } else {
                saveSubjectAndTopic(editSubjectRequest, userProfile);
            }
        }

        return Pair.of(true, "subject has been updated");
    }
}
