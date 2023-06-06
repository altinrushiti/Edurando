package de.app_solutions.Edurando.model;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String role;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String passwordRepeat;
    private final Boolean termsAgreed;
    private final Boolean privacyAgreed;

}
