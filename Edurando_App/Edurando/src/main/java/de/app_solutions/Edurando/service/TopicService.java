package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditTopicRequest;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public Topic getTopicByName(String name) {

        return topicRepository.findByName(name)
                .orElseThrow(()-> new UsernameNotFoundException(name));
    }

    public String updateTopicData(EditTopicRequest editTopicRequest) {

        return "it's work";
    }
}
