package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.ConfirmationCode;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.model.ResetPasswordRequest;
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
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        Pair<Boolean, String> response = resetPasswordService.forgotPassword(email);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }

    @PostMapping("/confirmCode")
    public ResponseEntity<String> confirmCode(@RequestParam("email") String email, @RequestParam("enteredCode") String enteredConfirmCode) {
        Pair<Boolean, String> response = resetPasswordService.confirmCode(email,enteredConfirmCode);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        Pair<Boolean, String> response = resetPasswordService.resetPassword(request);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }
}
