package de.app_solutions.Edurando.service;
import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.repository.TopicRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;
    @MockBean
    private UserProfileRepository userProfileRepository;
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addSubjectDataTest() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Teacher",
                "Max",
                "Musterfrau",
                email,
                "password");
        userProfile.setSubjects(new ArrayList<>());
        userProfile.setTopics(new ArrayList<>());

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setId(1L);
        editSubjectRequest.setSubject("Physics");
        editSubjectRequest.setTopic("Thermodynamics");
        when(userProfileRepository.findUserProfileById(editSubjectRequest.getId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, String> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertTrue(result.getFirst());
        assertEquals("subject has been updated", result.getSecond());
    }

    @Test
    public void addSubjectData_FieldIsEmptyTest() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Teacher",
                "Max",
                "Musterfrau",
                email,
                "password");

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setId(1L);
        editSubjectRequest.setSubject("Physics");
        editSubjectRequest.setTopic("");

        when(userProfileRepository.findUserProfileById(editSubjectRequest.getId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, String> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertFalse(result.getFirst());
        assertEquals("Please fill out all fields", result.getSecond());
        verify(subjectRepository, times(0)).saveAll(anyList());
        verify(topicRepository, times(0)).saveAll(anyList());
    }

    @Test
    public void SubjectDataIsExistTest() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Teacher",
                "Max",
                "Musterfrau",
                email,
                "password");

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setId(1L);
        editSubjectRequest.setSubject("Math");
        editSubjectRequest.setTopic("Algebra");

        when(userProfileRepository.findUserProfileById(editSubjectRequest.getId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, String> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertFalse(result.getFirst());
        assertEquals("Subject and topic are already exist", result.getSecond());
        verify(subjectRepository, times(0)).saveAll(anyList());
        verify(topicRepository, times(0)).saveAll(anyList());
    }
}



