package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.model.EditTopicRequest;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.repository.TopicRepository;
import de.app_solutions.Edurando.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class TopicController {

    private TopicService topicService;

    @GetMapping("/topics")
     List<Topic> getTopics(){

        return topicService.getAllTopics();
    }
    @GetMapping("/topic/{name}")
    Topic getTopic(@PathVariable String name){

        return topicService.getTopicByName(name);
    }

    @PostMapping("/updateTopicData")
    public String updatePersonalData(EditTopicRequest editTopicRequest) {
        return topicService.updateTopicData(editTopicRequest);
    }
}
