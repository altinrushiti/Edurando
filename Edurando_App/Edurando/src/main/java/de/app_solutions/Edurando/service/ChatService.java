package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ChatMessage;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.ChatMessageRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void processChatMessage(ChatMessage chatMessage) {


        // Get the sender and receiver user profiles
        Long senderId = chatMessage.getSender().getId();
        Long receiverId = chatMessage.getReceiver().getId();

        // Überprüfung, ob der Sender und Empfänger gültige Benutzerprofile sind
        Optional<UserProfile> senderProfile = userProfileRepository.findById(senderId);
        Optional<UserProfile> receiverProfile = userProfileRepository.findById(receiverId);

        if (senderProfile.isPresent() && receiverProfile.isPresent()) {
            // Create a unique chatroom identifier based on user IDs
            String chatRoomId = String.format("%s-%s",senderId,receiverId);

            // Set the chatroom identifier in the chat message
            chatMessage.setChatRoomId(chatRoomId);

            // Save the chat message
            chatMessageRepository.save(chatMessage);
        }
    }



}
