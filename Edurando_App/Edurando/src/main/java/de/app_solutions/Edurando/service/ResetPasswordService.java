package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.ConfirmationCode;
import de.app_solutions.Edurando.model.ResetPasswordRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.ConfirmationCodeRepository;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static de.app_solutions.Edurando.service.UserProfileService.USER_NOT_FOUND;

@Service
@Data
public class ResetPasswordService {
    private final UserProfileService userProfileService;
    private final EmailValidator emailValidator;
    private final UserProfileRepository userProfileRepository;
    private final EmailSender emailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordValidator passwordValidator;
    private final ConfirmationCodeRepository confirmationCodeRepository;

    private static final Duration EMAIL_SEND_INTERVAL = Duration.ofMinutes(5);
    private Map<String, LocalDateTime> lastEmailSentTimes = new HashMap<>();


    public Pair<Boolean, String> resetPassword(ResetPasswordRequest request) {
        Optional<UserProfile> userOpt = userProfileRepository.findUserProfileByUsername(request.getEmail());
        if (userOpt.isEmpty()) {  //!user.isPresent()
            return Pair.of(false, String.format(USER_NOT_FOUND, request.getEmail()));
        }
        UserProfile user = userOpt.get();
        Optional<ConfirmationCode> confirmationCode = confirmationCodeRepository.findByUser_Username(user.getUsername());
        if (confirmationCode.isEmpty()) {
            return Pair.of(false, "Passwordrequest-Token does not exist.");
        }
        if (!confirmationCode.get().isConfirmed()) {
            return Pair.of(false, "Passwordrequest-Token is not confirmed.");
        }
        String currentUserPw = user.getPassword();
        Pair<Boolean, String> newPwTuple = passwordValidator.passwordTest(request.getNewPassword(), request.getNewPasswordRepeat());
        if (bCryptPasswordEncoder.matches(request.getNewPassword(), currentUserPw)) {
            return Pair.of(false, "Password could not be changed because the new password is the same as the previous password.");
        }
        if (!newPwTuple.getFirst()) {
            return newPwTuple;
        }
        String encodedPassword = bCryptPasswordEncoder.encode(request.getNewPasswordRepeat());
        user.setPassword(encodedPassword);
        userProfileRepository.save(user);

        return Pair.of(true, "Password reset was successful.");
    }


    public Pair<Boolean, String> confirmCode(String email, String enteredConfirmCode) {
        Optional<ConfirmationCode> confirmationCodeOpt = confirmationCodeRepository.findByUser_Username(email);
        if (confirmationCodeOpt.isEmpty()) {
            return Pair.of(false, "Passwordrequest-Token does not exist.");
        }
        ConfirmationCode confirmationCode = confirmationCodeOpt.get();
        Optional<UserProfile> user = userProfileRepository.findUserProfileByUsername(confirmationCode.getUser().getUsername());
        if (user.isEmpty()) {
            return Pair.of(false, String.format(USER_NOT_FOUND, confirmationCode.getUser().getUsername()));
        }
        if (confirmationCode.isConfirmed()) {
            return Pair.of(false, "Request not valid.");
        }
        if (bCryptPasswordEncoder.matches(enteredConfirmCode, confirmationCode.getCode())) {
            confirmationCode.setConfirmed(true);
            confirmationCodeRepository.save(confirmationCode);
            return Pair.of(true, "Confirmation was successful.");
        } else {
            return Pair.of(false, "The entered confirmationcode is not correct.");
        }
    }


    @Transactional
    public Pair<Boolean, String> forgotPassword(String email) {
        LocalDateTime currentTime = LocalDateTime.now();
        Optional<UserProfile> userOpt = userProfileRepository.findUserProfileByUsername(email);
        if (userOpt.isPresent()) {
            UserProfile user = userOpt.get();
            if (!user.isEnabled()) {
                return Pair.of(false, "Your account is not verified.");
            }
            if (user.isLocked()) {
                return Pair.of(false, "Your account is locked. Please contact the support.");
            }
            Optional<ConfirmationCode> oldConfirmationCodesOpt = confirmationCodeRepository.findByUser_Username(user.getUsername());
            if(oldConfirmationCodesOpt.isPresent()) {
                ConfirmationCode confirmationCode = oldConfirmationCodesOpt.get();
                confirmationCodeRepository.deleteByUser_Username(confirmationCode.getUser().getUsername());
            }
            ConfirmationCode confirmationCode = new ConfirmationCode(
                    generateRandomCode(),
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user
            );
            LocalDateTime lastEmailSentTime = lastEmailSentTimes.getOrDefault(user.getUsername(), LocalDateTime.MIN);
            if (lastEmailSentTime.plus(EMAIL_SEND_INTERVAL).isAfter(currentTime)) {
                return Pair.of(false, "Please wait before requesting another password reset.");
            }

            //user.setConfirmationCode(confirmationCode);
            emailSender.send(user.getUsername(), buildEmail(confirmationCode.getUser().getUsername(), confirmationCode.getCode()), "Reset password request");

            // Aktualisiere lastEmailSentTime für den aktuellen Benutzer
            lastEmailSentTimes.put(email, LocalDateTime.now());

            String encodedConfirmCode = bCryptPasswordEncoder.encode(confirmationCode.getCode());
            confirmationCode.setCode(encodedConfirmCode);
            //confirmationCode.setUser(user);
            confirmationCodeRepository.save(confirmationCode);
            //  userProfileRepository.save(user);
            return Pair.of(true, "Email sent successfully.");
        } else {
            return Pair.of(false, String.format(USER_NOT_FOUND, email));
        }
    }

    public String generateRandomCode() {
        // Erstelle eine Instanz der Random-Klasse
        Random random = new Random();

        // Generiere eine zufällige 4-stellige Zahl
        int code = random.nextInt(9000) + 1000;

        // Konvertiere die Zahl in einen String und gib sie zurück
        return String.valueOf(code);
    }

    private String buildEmail(String email, String confirmCode) {

        UserProfile user = userProfileRepository.findUserProfileByUsername(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));

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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Reset your password</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hello " + user.getFirstName() + " " + user.getLastName() + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">We're sending you this email because you requested a password reset. Use the following code to reset your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" + confirmCode + "</p></blockquote>\nThis code will expire in 15 minutes. <p style=\"margin-bottom: 0\"> If you didn't request a password reset, you can ignore this email. Your password will not be changed.<p style=\"margin-bottom: 0\">Dear regards</p><p style=\"margin: 0\">The Edurando Team</p>" +
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
