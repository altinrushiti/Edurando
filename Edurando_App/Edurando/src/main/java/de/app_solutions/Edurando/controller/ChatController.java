package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
class ServerController {

    @MessageMapping("/process-message")
    @SendTo("/topic/messages")
    public ChatMessage processMessage(ChatMessage chatMessage) throws Exception{
        Thread.sleep(1000);
        return new ChatMessage(chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getMessage());
    }
}
