package de.app_solutions.Edurando.model;

import de.app_solutions.Edurando.service.PasswordValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPasswordRequest extends PasswordValidator {

    private String currentPassword;
    private String currentPasswordRepeat;
    private String newPassword;

}
