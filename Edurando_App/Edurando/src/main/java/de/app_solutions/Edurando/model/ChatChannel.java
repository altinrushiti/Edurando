package de.app_solutions.Edurando.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class ChatChannel {

    @Id
    @NotNull
    private String uuid;

    @OneToOne
    @JoinColumn(name = "userIdOne")
    private UserProfile userOne;

    @OneToOne
    @JoinColumn(name = "userIdTwo")
    private UserProfile userTwo;

    public ChatChannel(UserProfile userOne, UserProfile userTwo) {
        this.uuid = UUID.randomUUID().toString();
        this.userOne = userOne;
        this.userTwo = userTwo;
    }


}
