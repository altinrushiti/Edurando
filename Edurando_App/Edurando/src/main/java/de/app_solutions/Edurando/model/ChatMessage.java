package de.app_solutions.Edurando.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserProfile sender;

    @ManyToOne
    private UserProfile receiver;

    private String message;

    public ChatMessage(UserProfile sender, UserProfile receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}
