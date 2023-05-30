package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.ChatMessage;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public ChatMessage processChatMessage(ChatMessage chatMessage) {
        chatService.processChatMessage(chatMessage);
        return chatMessage;
    }

}
