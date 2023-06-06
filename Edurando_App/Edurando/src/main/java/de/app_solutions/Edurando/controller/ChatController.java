package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.ChatMessage;
import de.app_solutions.Edurando.model.ChatMessageRequest;
import de.app_solutions.Edurando.model.ChatSenderRequest;
import de.app_solutions.Edurando.model.UserProfileDTO;
import de.app_solutions.Edurando.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PutMapping("/editChatReceivers")
    public ResponseEntity<String> editChatSenders(@RequestBody ChatSenderRequest chatSenderRequest) {
        Pair<Boolean, String> result = chatService.editChatReceivers(chatSenderRequest);

        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }

    @GetMapping("/chatSenders/{id}")
    public List<UserProfileDTO> getChatSenders(@PathVariable Long id) {
        return chatService.getChatReceivers(id);
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody ChatMessageRequest chatMessageRequest) {
        Pair<Boolean, String> result = chatService.send(chatMessageRequest);
        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }

    @GetMapping("/chatHistory/{id}")
    public List<ChatMessage> getChatMessages(@PathVariable String id) {
        return chatService.getChatMessages(id);
    }
}

