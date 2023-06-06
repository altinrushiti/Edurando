package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.ChatMessageRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static de.app_solutions.Edurando.service.UserProfileService.USER_NOT_FOUND_BY_ID;

@Service
@AllArgsConstructor
public class ChatService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileDTOMapper userProfileDTOMapper;
    private final ChatMessageRepository chatMessageRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    public Pair<Boolean, String> send(ChatMessageRequest chatMessageRequest) {

        // Get the sender and receiver user profiles
        Optional<UserProfile> sender = userProfileRepository.findUserProfileById(chatMessageRequest.sender());
        Optional<UserProfile> receiver = userProfileRepository.findUserProfileById(chatMessageRequest.receiver());

        if (sender.isPresent() && receiver.isPresent()) {
            ChatMessage message = new ChatMessage(chatMessageRequest.sender(), chatMessageRequest.receiver(), chatMessageRequest.content(), LocalDateTime.now());
            chatMessageRepository.save(message);

            sender.get().getChatMessages().add(message);
            userProfileRepository.save(sender.get());
        } else {
            return Pair.of(false, "Sender or receiver not found");
        }
        return Pair.of(true, "Message sent successfully");
    }


    public Pair<Boolean, String> editChatReceivers(ChatSenderRequest chatSenderRequest) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(status -> {

            if (Objects.equals(chatSenderRequest.getId(), chatSenderRequest.getChatReceiver())) {
                return Pair.of(false, "You cannot chat with yourself");
            } else {
                Optional<UserProfile> receiver = userProfileRepository.findUserProfileById(chatSenderRequest.getId());
                Optional<UserProfile> sender = userProfileRepository.findUserProfileById(chatSenderRequest.getChatReceiver());
                if (receiver.isPresent() && sender.isPresent()) {

                    if (!sender.get().getChatReceivers().contains(chatSenderRequest.getId())) {
                        sender.get().getChatReceivers().add(chatSenderRequest.getId());
                    }

                    if (!receiver.get().getChatReceivers().contains(chatSenderRequest.getChatReceiver())) {
                        receiver.get().getChatReceivers().add(chatSenderRequest.getChatReceiver());
                    }

                    userProfileRepository.save(receiver.get());
                    userProfileRepository.save(sender.get());
                }
                return Pair.of(true, "Edited ChatReceivers successfully.");
            }
        });
    }

    public List<UserProfileDTO> getChatReceivers(Long id) {
        return userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)))
                .getChatReceivers()
                .stream()
                .map(l -> userProfileRepository.findUserProfileById(l).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))))
                .map(userProfileDTOMapper)
                .toList();
    }


    /*public List<ChatMessage> getChatMessages(String id) {
        String[] ids = id.split("-");
        List<ChatMessage> chatMessages = userProfileRepository.findUserProfileById(Long.parseLong(ids[0])).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))).getChatMessages();
        chatMessages.addAll(
                userProfileRepository.findUserProfileById(Long.parseLong(ids[1])).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))).getChatMessages()
        );
        return chatMessages.stream().filter(chatMessage -> (chatMessage.getSender().equals(Long.parseLong(ids[0])) && chatMessage.getReceiver().equals(Long.parseLong(ids[1]))) || (chatMessage.getReceiver().equals(Long.parseLong(ids[0])) && chatMessage.getSender().equals(Long.parseLong(ids[1])))).sorted(Comparator.comparing(ChatMessage::getTimeSent)).collect(Collectors.toList());
    }

     */
    public List<ChatMessage> getChatMessages(String id) {
        String[] ids = id.split("-");
        Long senderId = Long.parseLong(ids[0]);
        Long receiverId = Long.parseLong(ids[1]);

        UserProfile senderProfile = userProfileRepository.findUserProfileById(senderId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
        UserProfile receiverProfile = userProfileRepository.findUserProfileById(receiverId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));

        List<ChatMessage> senderMessages = senderProfile.getChatMessages();
        List<ChatMessage> receiverMessages = receiverProfile.getChatMessages();

        if (senderMessages == null) {
            senderMessages = new ArrayList<>();
        }
        if (receiverMessages == null) {
            receiverMessages = new ArrayList<>();
        }

        List<ChatMessage> allChatMessages = new ArrayList<>();
        allChatMessages.addAll(senderMessages);
        allChatMessages.addAll(receiverMessages);

        return allChatMessages.stream()
                .filter(chatMessage -> (chatMessage.getSender().equals(senderId) && chatMessage.getReceiver().equals(receiverId))
                        || (chatMessage.getReceiver().equals(senderId) && chatMessage.getSender().equals(receiverId)))
                .sorted(Comparator.comparing(ChatMessage::getTimeSent))
                .collect(Collectors.toList());
    }

}

