package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.service.ChatService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ChatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatController chatController;

    @MockBean
    private ChatService chatService;
    @Test
    public void testEditChatSenders_Success() throws Exception {
        ChatReceiverRequest request = new ChatReceiverRequest(1L, 2L);
        String requestJson = new ObjectMapper().writeValueAsString(request);
        String expectedResponse = "Success message";

        when(chatController.editChatReceivers(any(ChatReceiverRequest.class))).thenReturn(ResponseEntity.ok(expectedResponse));

        mockMvc.perform(put("/api/v1/editChatReceivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testEditChatSenders_Failure() throws Exception {
        ChatReceiverRequest request = new ChatReceiverRequest(1L, 2L);
        String requestJson = new ObjectMapper().writeValueAsString(request);
        String expectedResponse = "Error message";

        when(chatController.editChatReceivers(any(ChatReceiverRequest.class))).thenReturn(ResponseEntity.badRequest().body(expectedResponse));

        mockMvc.perform(put("/api/v1/editChatReceivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetChatReceivers() throws Exception {
        List<UserProfileDTO> chatReceivers = new ArrayList<>();
        UserProfileDTO userProfileDTO = new UserProfileDTO(
                1L, // id
                "John", // firstName
                "Doe", // lastName
                "123456789", // mobile
                "profile_pic.jpg", // profilePictureReference
                "Bio", // personalBiography
                4.5f, // rating
                "Male", // gender
                "Location", // tutoringLocation
                "username", // username
                "role", // role
                List.of(), // subjects
                List.of(), // topics
                List.of(), // ratings
                new Address() // address
        );
        chatReceivers.add(userProfileDTO);

        when(chatController.getChatReceivers(eq(userProfileDTO.id()))).thenReturn(chatReceivers);
        mockMvc.perform(get("/api/v1/chatReceivers/{id}", userProfileDTO.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(chatReceivers.size())); // Check ob Länge des JSON-Arrays im HTTP-Antwortkörper mit der Größe der chatReceivers-Liste übereinstimmt
    }

    @Test
    public void testSend_Success() throws Exception {
        ChatMessageRequest request = new ChatMessageRequest(1L, 2L, "Hello");
        String requestJson = new ObjectMapper().writeValueAsString(request);
        String expectedResponse = "Success message";

        when(chatController.send(any(ChatMessageRequest.class))).thenReturn(ResponseEntity.ok(expectedResponse));

        mockMvc.perform(post("/api/v1/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
    @Test
    public void testSend_Failure() throws Exception {
        ChatMessageRequest request = new ChatMessageRequest(1L, 2L, "Hello");
        String requestJson = new ObjectMapper().writeValueAsString(request);
        String expectedResponse = "Error message";

        when(chatController.send(any(ChatMessageRequest.class))).thenReturn(ResponseEntity.badRequest().body(expectedResponse));

        mockMvc.perform(post("/api/v1/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetChatMessages() throws Exception {
        String id = "1-2";
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage());
        chatMessages.add(new ChatMessage());

        when(chatController.getChatMessages(eq(id))).thenReturn(chatMessages);

        mockMvc.perform(get("/api/v1/chatHistory/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(chatMessages.size()));
    }


}
