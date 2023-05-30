package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.ChatChannelRepository;
import de.app_solutions.Edurando.repository.ChatMessageRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatChannelRepository chatChannelRepository;
    @Autowired
    private  UserProfileService userProfileService;

    private String
    getExistingChannel(ChatChannelInitializationDTO chatChannelInitializationDTO){
        List<ChatChannel> channel = chatChannelRepository
                .findExistingChannel(chatChannelInitializationDTO.getUserIdTwo(),
                        chatChannelInitializationDTO.getUserIdTwo() );

        return (channel != null && !channel.isEmpty()) ? channel.get(0).getUuid() : null;

    }

    private  String newChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO)
    throws UsernameNotFoundException {

        ChatChannel channel = new ChatChannel(userProfileService.getUserById(chatChannelInitializationDTO.getUserIdOne()),userProfileService.getUserById(chatChannelInitializationDTO.getUserIdTwo()));

        chatChannelRepository.save(channel);

        return channel.getUuid();
    }
    public String establishChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO)
            throws IsSameUserException, BeansException, UsernameNotFoundException {

        if (chatChannelInitializationDTO.getUserIdTwo() == chatChannelInitializationDTO.getUserIdTwo()) {
            throw new IsSameUserException();
        }
        String uuid = getExistingChannel(chatChannelInitializationDTO);

        return (uuid != null) ? uuid : newChatSession(chatChannelInitializationDTO);
    }






}
/*    public void processChatMessage(ChatMessage chatMessage) {


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
    }*/