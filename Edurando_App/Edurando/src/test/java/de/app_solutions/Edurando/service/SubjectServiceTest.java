package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.repository.TopicRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SubjectServiceTest  {

    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


    @InjectMocks
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
        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Musterfrau",
                email,
                "password");

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setUserId(1L);
        editSubjectRequest.setSubject("Physics");
        editSubjectRequest.setTopic("Thermodynamics");
        when(userProfileRepository.findUserProfileById(editSubjectRequest.getUserId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, List<String>> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertTrue(result.getFirst());
        assertEquals(List.of("subject has been updated"), result.getSecond());
        verify(subjectRepository, times(1)).saveAll(anyList());
        verify(topicRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void addSubjectData_FieldIsEmptyTest() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Musterfrau",
                email,
                "password");

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setUserId(1L);
        editSubjectRequest.setSubject("Physics");
        editSubjectRequest.setTopic("");

        when(userProfileRepository.findUserProfileById(editSubjectRequest.getUserId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, List<String>> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertFalse(result.getFirst());
        assertEquals(List.of("Please fill out all fields"), result.getSecond());
        verify(subjectRepository, times(0)).saveAll(anyList());
        verify(topicRepository, times(0)).saveAll(anyList());
    }

    @Test
    public void SubjectDataIsExistTest() {
        String email = "max.musterfraun@example.com";
        // given
        UserProfile userProfile = new UserProfile("Student",
                "Max",
                "Musterfrau",
                email,
                "password");

        EditSubjectRequest editSubjectRequest = new EditSubjectRequest();
        editSubjectRequest.setUserId(1L);
        editSubjectRequest.setSubject("Math");
        editSubjectRequest.setTopic("Algebra");

        when(userProfileRepository.findUserProfileById(editSubjectRequest.getUserId())).thenReturn(Optional.of(userProfile));
        when(userProfileRepository.findSubjectsByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Math"));
        when(userProfileRepository.findTopicByUserProfileId(editSubjectRequest.getUserId())).thenReturn(List.of("Algebra"));

        // when
        Pair<Boolean, List<String>> result = subjectService.addSubjectData(editSubjectRequest);

        // then
        assertFalse(result.getFirst());
        assertEquals(List.of("Subject and topic are already exist"), result.getSecond());
        verify(subjectRepository, times(0)).saveAll(anyList());
        verify(topicRepository, times(0)).saveAll(anyList());
    }
}



