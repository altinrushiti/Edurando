package de.app_solutions.Edurando.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class ConfirmationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private boolean confirmed;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    @OneToOne
    private UserProfile user;
    public ConfirmationCode(String code, boolean confirmed, LocalDateTime createdAt, LocalDateTime expiresAt, UserProfile user) {

        this.code = code;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }


}
