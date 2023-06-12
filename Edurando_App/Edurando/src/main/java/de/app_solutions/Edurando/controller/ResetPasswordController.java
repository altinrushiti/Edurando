package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.service.ResetPasswordService;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@Data
@CrossOrigin(origins = "http://localhost:5173")
public class ResetPasswordController {
    private final ResetPasswordService resetPasswordService;

    @PostMapping("/forgotPassword")
    public ResponseEntity<Pair<Boolean,String>> forgotPassword(@RequestParam("email") String email) {
        Pair<Boolean, String> response = resetPasswordService.forgotPassword(email);
        if (response.getFirst()) return ResponseEntity.ok(response);
        else return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/confirmCode")
    public ResponseEntity<Pair<Boolean,String>> confirmCode(@RequestParam("confirmCode") String confirmCode,@RequestParam("enteredConfirmCode") String enteredConfirmCode) {
        Pair<Boolean, String> response = resetPasswordService.confirmCode(confirmCode,enteredConfirmCode);
        if (response.getFirst()) return ResponseEntity.ok(response);
        else return ResponseEntity.badRequest().body(response);
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<Pair<Boolean,String>> resetPassword(@RequestParam("email") String email,@RequestParam("newPassword") String newPassword,@RequestParam("newPasswordRepeat") String newPasswordRepeat) {
        Pair<Boolean, String> response = resetPasswordService.resetPassword(email,newPassword,newPasswordRepeat);
        if (response.getFirst()) return ResponseEntity.ok(response);

        else return ResponseEntity.badRequest().body(response);
    }
}
