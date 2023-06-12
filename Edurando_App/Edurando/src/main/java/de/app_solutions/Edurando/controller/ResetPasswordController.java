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

    @PostMapping("/resetPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        Pair<Boolean, String> response = resetPasswordService.forgotPassword(email);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }
}
