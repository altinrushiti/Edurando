package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.*;
import de.app_solutions.Edurando.repository.ChatMessageRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.app_solutions.Edurando.service.UserProfileService.USER_NOT_FOUND_BY_ID;

@Service
@AllArgsConstructor
public class ChatService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileDTOMapper userProfileDTOMapper;
    private final ChatMessageRepository chatMessageRepository;

    public Pair<Boolean, String> send(ChatMessageRequest chatMessageRequest) {


        // Get the sender and receiver user profiles
        Optional<UserProfile> sender = userProfileRepository.findUserProfileById(chatMessageRequest.sender());
        Optional<UserProfile> receiver = userProfileRepository.findUserProfileById(chatMessageRequest.receiver());

        if (sender.isPresent() && receiver.isPresent()) {
            ChatMessage message = new ChatMessage(chatMessageRequest.sender(), chatMessageRequest.receiver(), chatMessageRequest.content(), LocalDateTime.now());
            chatMessageRepository.save(message);
            sender.get().getChatMessages().add(message);
            userProfileRepository.save(sender.get());
        }
        return Pair.of(true, "Success");
    }


    public Pair<Boolean, String> editChatSenders(ChatSenderRequest chatSenderRequest) {
        if (Objects.equals(chatSenderRequest.getId(), chatSenderRequest.getChatSender())) {
            return Pair.of(false, "You cannot chat with yourself");
        } else {
            Optional<UserProfile> user = userProfileRepository.findUserProfileById(chatSenderRequest.getId());
            Optional<UserProfile> sender = userProfileRepository.findUserProfileById(chatSenderRequest.getChatSender());
            if (user.isPresent() && sender.isPresent()) {

                if (!user.get().getChatSenders().contains(chatSenderRequest.getChatSender())) {
                    user.get().getChatSenders().add(chatSenderRequest.getChatSender());
                }

                if (!sender.get().getChatSenders().contains(chatSenderRequest.getId())) {
                    sender.get().getChatSenders().add(chatSenderRequest.getId());
                }


                userProfileRepository.save(user.get());
                userProfileRepository.save(sender.get());
            }

            return Pair.of(true, "Success");
        }
    }

    public List<UserProfileDTO> getChatSenders(Long id) {
        return userProfileRepository.findUserProfileById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)))
                .getChatSenders()
                .stream()
                .map(l -> userProfileRepository.findUserProfileById(l).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))))
                .map(userProfileDTOMapper)
                .toList();
    }

    public List<ChatMessage> getChatMessages(String id) {
        String[] ids = id.split("-");
        List<ChatMessage> chatMessages = userProfileRepository.findUserProfileById(Long.parseLong(ids[0])).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))).getChatMessages();
        chatMessages.addAll(
                userProfileRepository.findUserProfileById(Long.parseLong(ids[1])).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))).getChatMessages()
        );
        return chatMessages.stream().filter(chatMessage -> (chatMessage.getSender().equals(Long.parseLong(ids[0])) && chatMessage.getReceiver().equals(Long.parseLong(ids[1]))) || (chatMessage.getReceiver().equals(Long.parseLong(ids[0])) && chatMessage.getSender().equals(Long.parseLong(ids[1])))).sorted(Comparator.comparing(ChatMessage::getTimeSent)).collect(Collectors.toList());
    }
}

