package de.app_solutions.Edurando.service;


import de.app_solutions.Edurando.model.ConfirmationToken;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.Role;
import de.app_solutions.Edurando.model.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserProfileService userProfileService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final PasswordValidator passwordValidator;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.testMail(request.getEmail());
        boolean passwordsMatch = passwordValidator.matchTest(request.getPassword(), request.getPasswordRepeat());
        boolean passwordsLength = passwordValidator.lengthTest(request.getPassword());
        boolean passwordHasUpperAndLowerCase = passwordValidator.upperLowerCaseTest(request.getPassword());
        boolean passwordHasDigit = passwordValidator.digitTest(request.getPassword());
        boolean passwordHasSpecialChar = passwordValidator.specialCharTest(request.getPassword());
        boolean mailIsExists = emailValidator.mailExists(request.getEmail());

        int pw_count = 0;
        StringBuilder sb = new StringBuilder();

        if (mailIsExists) {
            sb.append("Email Exists\n");
            pw_count++;
        } if (!isValidEmail) {
            sb.append("E-Mail not valid\n");
            pw_count++;
        } if (!passwordsMatch) {
            sb.append("Passwords do not match\n");
            pw_count++;
        } if (!passwordsLength) {
            sb.append("Password needs minimum length of 8\n");
            pw_count++;
        } if (!passwordHasUpperAndLowerCase) {
            sb.append("Password needs at least 1 upper and 1 lower case character\n");
            pw_count++;
        } if (!passwordHasDigit) {
            sb.append("Password needs at least 1 digit\n");
            pw_count++;
        } if (!passwordHasSpecialChar) {
            sb.append("Password needs at least 1 special character\n");
            pw_count++;
        } if (pw_count > 0) {
            String final_msg = sb.toString();
            System.err.printf("(%b , %s)\n", false, final_msg);
            return String.format("(%b , %s)\n", false, final_msg);

        } else {
            String token = userProfileService.signUpUser(new UserProfile(
                    request.getRole(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword()
                    )
            );
            String link = String.format("http://localhost:9001/api/v1/confirm/?token=%s", token);
            emailSender.send(request.getEmail(), buildEmail(request, link));

            System.err.printf("(%b, Registration was successful)\n", true);
            return String.format("(%b, Registration was successful)\n", true);
        }
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmationAt(token);
        userProfileService.enableAppUser(confirmationToken.getUser().getUsername());
        return "verifizierung_erfolgreich";

    }

    private String buildEmail(RegistrationRequest user, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Bestätige deine E-Mail</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hallo " + user.getFirstName() + " " + user.getLastName() + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Vielen Dank für die Registrierung bei Edurando! Bitte klicken Sie auf den folgenden Link, um ihr Account zu aktivieren:</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Jetzt aktivieren</a> </p></blockquote>\nDer Link läuft in 15 min ab.<p style=\"margin-bottom: 0\">Mit freundlichen Grüßen</p><p style=\"margin: 0\">Ihr Edurando - Team</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
