package de.app_solutions.Edurando.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "senderId")
    private UserProfile sender;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private UserProfile receiver;

    @NotNull
    private Date timeSent;

    @NotNull
    private String contents;


    public ChatMessage(UserProfile sender, UserProfile receiver, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
        this.timeSent = new Date();
    }

}
