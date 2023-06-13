package de.app_solutions.Edurando.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private String contents;

    private long fromUserId;

    private long toUserId;

}
