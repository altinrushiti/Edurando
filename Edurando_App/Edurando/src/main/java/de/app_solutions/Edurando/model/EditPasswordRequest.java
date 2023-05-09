package de.app_solutions.Edurando.model;

import de.app_solutions.Edurando.service.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EditPasswordRequest extends PasswordValidator {

    private final Long id;
    private final String currentPassword;
    private final String newPassword;
    private final String newPasswordRepeat;

}
