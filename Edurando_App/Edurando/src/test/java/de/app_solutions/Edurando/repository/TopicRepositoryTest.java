package de.app_solutions.Edurando.repository;


import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;


    @Test
    void testFindByName(){
        Topic topic = new Topic();
        topic.setName("Algebra");
        topicRepository.save(topic);

        Optional<Topic> foundTopic = topicRepository.findByName("Algebra");

        assertTrue(foundTopic.isPresent());
        assertEquals(topic.getId(),foundTopic.get().getId());
        assertEquals(topic.getName(), foundTopic.get().getName());
    }

    }

