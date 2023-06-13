package de.app_solutions.Edurando.model;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResetPasswordRequest {

    private String email;
    String newPassword;
    String newPasswordRepeat;

}
