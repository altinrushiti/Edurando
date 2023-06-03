package de.app_solutions.Edurando.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private Long sender;

    private Long receiver;

    private LocalDateTime timeSent;

    private String contents;


    public ChatMessage(Long sender, Long receiver, String contents, LocalDateTime timeSent) {
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
        this.timeSent = timeSent;
    }

}
