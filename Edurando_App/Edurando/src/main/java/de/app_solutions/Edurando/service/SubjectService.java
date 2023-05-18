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
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.User;
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

    public void saveSubjectAndTopic(EditSubjectRequest editSubjectRequest, UserProfile userProfile) {


        List<UserProfile> userProfiles = new ArrayList<>();

        List<Subject> subjects = new ArrayList<>();

        List<Topic> topics = new ArrayList<>();

        Subject newSubject = new Subject();


        String subjectName = ((editSubjectRequest.getSubject().charAt(0) + "").toUpperCase() + editSubjectRequest.getSubject().substring(1)).trim();
        String topicName = ((editSubjectRequest.getTopic().charAt(0) + "").toUpperCase() + editSubjectRequest.getTopic().substring(1)).trim();

        // create Subject & add Attributes to the new object
        newSubject.setName(subjectName);
        newSubject.setUserProfiles(userProfiles);


        // create Topic & add Attributes to the new Object
        Topic newTopic = new Topic();
        newTopic.setName(topicName);
        newTopic.setUserProfiles(userProfiles);


        //add userprofile to us_list
        userProfiles.add(userProfile);


        // add List of UserProfile to the newSubject
        newSubject.setUserProfiles(userProfiles);


        // add topic to the list  and then to the subject
        topics.add(newTopic);
        newSubject.setTopics(topics);


        // we ve to  update the last topic
        // because it has the topic witch not has the right subject .
        // we remove the new topic to update it

        topics.remove(newTopic);


        // add the right subject
        newTopic.setSubject(newSubject);

        // add topic again  to the list
        topics.add(newTopic);
        newSubject.setTopics(topics);


        subjects.add(newSubject);

        userProfile.setTopics(topics);


        userProfile.setSubjects(subjects);


        topicRepository.saveAll(topics);

        subjectRepository.saveAll(subjects);


        userProfileRepository.save(userProfile);
    }

    public Pair<Boolean, List<String>> addSubjectData(EditSubjectRequest editSubjectRequest) {

        // call userprofile

        UserProfile userProfile = userProfileRepository
                .findUserProfileById(editSubjectRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException
                        (String.format(USER_NOT_FOUND_BY_ID, editSubjectRequest.getUserId())));

        if (editSubjectRequest.getSubject().isEmpty() || editSubjectRequest.getTopic().isEmpty()) {

            Pair<Boolean, List<String>> tuple = Pair.of(false, List.of("Please fill out all fields"));
            System.err.println(tuple);
            return tuple;
        }

        List<String> subjectsInUser = userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getUserId());
        List<String> topicsInUser = userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getUserId());

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
                return Pair.of(false, List.of("Subject and topic are already exist"));

            }
            else {

                saveSubjectAndTopic(editSubjectRequest, userProfile);

            }
        }








        return Pair.of(true, List.of("subject has been updated"));


    }



}