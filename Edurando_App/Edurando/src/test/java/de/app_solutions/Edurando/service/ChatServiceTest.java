package de.app_solutions.Edurando.service;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.eq;
        import static org.mockito.Mockito.when;

        import de.app_solutions.Edurando.model.*;
        import de.app_solutions.Edurando.repository.ChatMessageRepository;
        import de.app_solutions.Edurando.repository.UserProfileRepository;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.data.util.Pair;
        import org.springframework.test.context.junit4.SpringRunner;

        import javax.transaction.Transactional;
        import java.time.LocalDateTime;
        import java.util.List;
        import java.util.Optional;
        import java.util.Random;
        import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    //@Autowired
    //private ChatMessageRepository chatMessageRepository;

    private UserProfile sender;
    private UserProfile receiver1;
    private UserProfile receiver2;

    @BeforeEach
    public void setUp() {
        sender = new UserProfile("Student","Paul","Musterfrau","paul.musterfrau@stud.th-luebeck.de","Test_123!");
        sender.setId(1L);
        receiver1 = new UserProfile("Lehrer","Raul","Mustermann","raul.mustermann@stud.th-luebeck.de","Test_123.");
        receiver1.setId(2L);
        receiver2 = new UserProfile("Lehrer", "Alice", "Johnson", "alice.johnson@example.com", "Test_123");
        receiver2.setId(3L);
        when(userProfileRepository.findUserProfileById(1L)).thenReturn(Optional.of(sender));
        when(userProfileRepository.findUserProfileById(2L)).thenReturn(Optional.of(receiver1));
        when(userProfileRepository.findUserProfileById(3L)).thenReturn(Optional.of(receiver2));
    }

    @Test
    public void testSendAndGetChatMessages() {

        ChatMessageRequest request1 = new ChatMessageRequest(sender.getId(), receiver1.getId(), "Hallo1");
        ChatMessageRequest request2 = new ChatMessageRequest(sender.getId(), receiver2.getId(), "Hallo2");
        chatService.send(request1);
        chatService.send(request2);
        //List<ChatMessage> l1= chatMessageRepository.findChatMessagesBySenderOrReceiver(1L,2L);
        //List<ChatMessage> l2= chatMessageRepository.findChatMessagesBySenderOrReceiver(1L,3L);

        List<ChatMessage> l1 = chatService.getChatMessages(String.format("%s-%s",sender.getId(),receiver1.getId()));
        List<ChatMessage> l2 = chatService.getChatMessages(String.format("%s-%s",sender.getId(),receiver2.getId()));

        assertEquals(l1.get(0).getContents(),"Hallo1");
        assertEquals(l2.get(0).getContents(),"Hallo2");
        //assertEquals(chat2Content,"Hallo2");
    }


    @Test
    public void testEditChatReceiversAndGetChatReceivers() {
        ChatSenderRequest chatSenderRequest1 = new ChatSenderRequest(sender.getId(), receiver1.getId());
        chatService.editChatReceivers(chatSenderRequest1);

        ChatSenderRequest chatSenderRequest2 = new ChatSenderRequest(sender.getId(), receiver2.getId());
        chatService.editChatReceivers(chatSenderRequest2);

        List<UserProfileDTO> chatReceivers = chatService.getChatReceivers(sender.getId());

        assertEquals(2, chatReceivers.size());
        List<String> receiverNames = chatReceivers.stream().map(UserProfileDTO::firstName).toList();
        assertTrue(receiverNames.contains("Raul"));
        assertTrue(receiverNames.contains("Alice"));
    }


}